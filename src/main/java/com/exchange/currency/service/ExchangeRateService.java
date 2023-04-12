package com.exchange.currency.service;

import com.exchange.currency.model.CurrencyExchangeRate;
import com.exchange.currency.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ExchangeRateService {
    private static final String CURRENCY_EUR = "EUR";

    private final List<CurrencyExchangeRate> currencyExchangeRates;

    @Autowired
    public ExchangeRateService(List<CurrencyExchangeRate> currencyExchangeRates) {
        this.currencyExchangeRates = currencyExchangeRates;
    }

    public List<CurrencyExchangeRate> getAllCurrencyExchangeRates() {
        return currencyExchangeRates;
    }

    public BigDecimal getExchangeRate(String initialCurrency, String finalCurrency) {
        if (finalCurrency.equalsIgnoreCase(CURRENCY_EUR)) {
            return findCurrencyExchangeRate(initialCurrency);
        } else if (initialCurrency.equalsIgnoreCase(CURRENCY_EUR)) {
            BigDecimal eurExchangeRate = findCurrencyExchangeRate(CURRENCY_EUR);
            BigDecimal finalCurrencyExchangeRateToEur = findCurrencyExchangeRate(finalCurrency);
            return eurExchangeRate.divide(finalCurrencyExchangeRateToEur, Utils.SCALE, RoundingMode.HALF_EVEN).stripTrailingZeros();
        } else {
            BigDecimal initialCurrencyExchangeRate = findCurrencyExchangeRate(initialCurrency);
            BigDecimal finalCurrencyExchangeRate = findCurrencyExchangeRate(finalCurrency);
            return initialCurrencyExchangeRate.divide(finalCurrencyExchangeRate, Utils.SCALE, RoundingMode.HALF_EVEN).stripTrailingZeros();
        }
    }

    public BigDecimal findCurrencyExchangeRate(String currency) {
        return currencyExchangeRates.stream().filter(s -> s.getCurrency().equalsIgnoreCase(currency)).findFirst().map(CurrencyExchangeRate::getExchangeRate).get();
    }
}
