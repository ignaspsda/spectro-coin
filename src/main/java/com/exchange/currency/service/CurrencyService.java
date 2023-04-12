package com.exchange.currency.service;

import com.exchange.currency.exception.InvalidCurrencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CurrencyService {

    private final List<String> currencies;

    @Autowired
    public CurrencyService(List<String> currencies) {
        this.currencies = currencies;
    }

    public void validateCurrencies(String initialCurrency, String finalCurrency) {
        List<String> nonExistentCurrencies = Stream.of(initialCurrency, finalCurrency).filter(c -> !currencies.contains(c)).toList();

        if (nonExistentCurrencies.size() > 0) {
            String excResult = String.join(", ", nonExistentCurrencies);
            throw new InvalidCurrencyException(String.format("One or both currencies are not allowed. Check currencies: %s", excResult));
        }

        if (initialCurrency.equalsIgnoreCase(finalCurrency)) {
            throw new InvalidCurrencyException(String.format("You can not exchange from %s to %s", initialCurrency, finalCurrency));
        }
    }

}
