package com.exchange.currency.service;

import com.exchange.currency.exception.InvalidCurrencyException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CurrencyServiceTest {

    @Test
    public void testValidateCurrencies_validCurrencies_shouldNotThrowException() {
        List<String> currencies = List.of("BTC", "EUR", "USD");
        CurrencyService currencyService = new CurrencyService(currencies);

        Assertions.assertDoesNotThrow(() -> currencyService.validateCurrencies("USD", "EUR"));
    }

    @Test
    public void testValidateCurrencies_oneInvalidCurrency_shouldThrowException() {
        List<String> currencies = List.of("BTC", "EUR", "USD");
        CurrencyService currencyService = new CurrencyService(currencies);

        Assertions.assertThrows(InvalidCurrencyException.class, () -> currencyService.validateCurrencies("AUD", "EUR"));
        Assertions.assertThrows(InvalidCurrencyException.class, () -> currencyService.validateCurrencies("USD", "AUD"));
    }

    @Test
    public void testValidateCurrencies_bothInvalidCurrencies_shouldThrowException() {
        List<String> currencies = List.of("BTC", "EUR", "USD");
        CurrencyService currencyService = new CurrencyService(currencies);

        Assertions.assertThrows(InvalidCurrencyException.class, () -> currencyService.validateCurrencies("AUD", "CAD"));
    }

    @Test
    public void testValidateCurrencies_sameCurrencies_shouldThrowException() {
        List<String> currencies = List.of("BTC", "EUR", "USD");
        CurrencyService currencyService = new CurrencyService(currencies);

        Assertions.assertThrows(InvalidCurrencyException.class, () -> currencyService.validateCurrencies("USD", "USD"));
    }

}
