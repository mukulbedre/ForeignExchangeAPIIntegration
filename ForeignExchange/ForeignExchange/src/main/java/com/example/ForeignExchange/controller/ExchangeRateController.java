package com.example.ForeignExchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ForeignExchange.model.ExchangeRateResponse;
import com.example.ForeignExchange.service.ExchangeRateService;

@RestController
@RequestMapping("/fx")
public class ExchangeRateController {

	@Autowired
	ExchangeRateService service;

	@GetMapping("/{targetCurrency}")
	public ExchangeRateResponse getTargetRate(@PathVariable String targetCurrency) {
		return service.getExchangeRates(targetCurrency);
	}

	@GetMapping()
	public ExchangeRateResponse getRates() {
		return service.getExchangeRates("USD,GBP,JPY,CZK");

	}

}
