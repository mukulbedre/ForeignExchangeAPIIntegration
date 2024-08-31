package com.example.ForeignExchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.ForeignExchange.model.ExchangeRate;
import com.example.ForeignExchange.repository.ExchangeRateRepository;

@SpringBootTest
class ForeignExchangeApplicationTests {

	@Autowired
	private ExchangeRateRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testFindTop3ByTargetCurrencyAndDateOrderByDateDesc() {
		// Arrange
		LocalDate date = LocalDate.of(2024, 8, 22);
		String targetCurrency = "GBP";

		// Setup test data
		ExchangeRate rate1 = new ExchangeRate(1L, "USD", "GBP", 0.7500, LocalDate.of(2024, 8, 20));
		ExchangeRate rate2 = new ExchangeRate(2L, "EUR", "GBP", 0.7550, LocalDate.of(2024, 8, 21));
		ExchangeRate rate3 = new ExchangeRate(3L, "USD", "GBP", 0.7600, LocalDate.of(2024, 8, 22));
		ExchangeRate rate4 = new ExchangeRate(4L, "EUR", "GBP", 0.7650, LocalDate.of(2024, 8, 23));
		ExchangeRate rate5 = new ExchangeRate(5L, "USD", "GBP", 0.7700, LocalDate.of(2024, 8, 24));

		repository.saveAll(Arrays.asList(rate1, rate2, rate3, rate4, rate5));

		// Act
		List<ExchangeRate> rates = repository.findTop3ByTargetCurrencyAndDateOrderByDateDesc(targetCurrency, date);

		assertThat(1).isEqualTo(rates.size());
		assertThat(rate3.getId()).isEqualTo(rates.get(0).getId());
	}

	@Test
	public void testFindTop3ByTargetCurrencyAndDateOrderByDateDesc_NoData() {
		// Arrange
		LocalDate date = LocalDate.of(2024, 8, 22);
		String targetCurrency = "JPY"; // No data for JPY

		// Act
		List<ExchangeRate> rates = repository.findTop3ByTargetCurrencyAndDateOrderByDateDesc(targetCurrency, date);

		// Assert
		assertThat(rates.isEmpty()).isTrue();
	}

}
