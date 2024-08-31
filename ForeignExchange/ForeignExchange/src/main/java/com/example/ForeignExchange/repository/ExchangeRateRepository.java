package com.example.ForeignExchange.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ForeignExchange.model.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

	List<ExchangeRate> findTop3ByTargetCurrencyAndDateOrderByDateDesc(String targetCurrency, LocalDate date);

	boolean existsByDateAndTargetCurrency(LocalDate date, String targetCurrency);
}