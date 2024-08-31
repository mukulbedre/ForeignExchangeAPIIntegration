package com.example.ForeignExchange.model;

import java.time.LocalDate;
import java.util.Map;

public class ExchangeRateResponse {

	private LocalDate date;
	private String base;
	private Map<String, Double> rates;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

}
