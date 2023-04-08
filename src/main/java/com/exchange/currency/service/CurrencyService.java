package com.exchange.currency.service;

import com.exchange.currency.exception.InvalidCurrencyException;
import com.exchange.currency.model.CurrencyExchangeRate;
import com.exchange.currency.payload.ExchangeRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    public void currenciesAreValid(List<String> currencyList, String initialCurrency, String finalCurrency) {
        validateCurrency(currencyList, initialCurrency);
        validateCurrency(currencyList, finalCurrency);
    }

    private void validateCurrency(List<String> currencyList, String currency) {
        boolean exists = currencyList.stream().anyMatch(s -> s.equals(currency));

        if (!exists) {
            throw new InvalidCurrencyException(String.format("Currency %s not allowed!", currency));
        }
    }
}
