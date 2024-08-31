package com.example.ForeignExchange.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ForeignExchange.model.ExchangeRate;
import com.example.ForeignExchange.model.ExchangeRateResponse;
import com.example.ForeignExchange.repository.ExchangeRateRepository;

@Service
public class ExchangeRateService {

	@Autowired
	private ExchangeRateRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;


	public ExchangeRateResponse getExchangeRates(String targetCurrency) {
		LocalDate today = LocalDate.now();

		if (!repository.existsByDateAndTargetCurrency(today, targetCurrency)) {
			// Fetch from external API
			String url = "https://api.frankfurter.app/latest?to=" + targetCurrency;
			ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

			// Save to database
			if (response != null) {
				for (Map.Entry<String, Double> entry : response.getRates().entrySet()) {
					ExchangeRate rate = new ExchangeRate();
					rate.setSourceCurrency(response.getBase());
					rate.setTargetCurrency(entry.getKey());
					rate.setRate(entry.getValue());
					rate.setDate(response.getDate());
					repository.save(rate);
				}
			}
			
			return response;
		}

		String[] currencies = targetCurrency.split(",");
	    Map<String, Double> rateMap = new HashMap<>();
	    
	    for (String target : currencies) {
	        List<ExchangeRate> rates = repository.findTop3ByTargetCurrencyAndDateOrderByDateDesc(targetCurrency.trim(),today);
	        
	        if (!rates.isEmpty()) {
	            // Assuming you want to store the most recent rate for each currency
	            ExchangeRate mostRecentRate = rates.get(0);  // Get the most recent rate
	            rateMap.put(target.trim(), mostRecentRate.getRate());
	        }
	    }

		ExchangeRateResponse response = new ExchangeRateResponse();
		response.setDate(today);
		response.setBase("EUR");
		response.setRates(rateMap);
		return response;
	}

}
