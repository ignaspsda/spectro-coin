package com.exchange.currency.service;

import com.exchange.currency.payload.ExchangeRequest;
import com.exchange.currency.payload.ExchangeResponse;
import com.exchange.currency.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeService {
    private final CurrencyService currencyService;
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeService(CurrencyService currencyService, ExchangeRateService exchangeRateService) {
        this.currencyService = currencyService;
        this.exchangeRateService = exchangeRateService;
    }

    public ExchangeResponse exchangeCurrency(ExchangeRequest exchangeRequest) {

        String initialCurrency = exchangeRequest.getInitialCurrency();
        String finalCurrency = exchangeRequest.getFinalCurrency();
        currencyService.validateCurrencies(initialCurrency, finalCurrency);
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(initialCurrency, finalCurrency);
        BigDecimal finalQuantity = exchangeRequest.getQuantity().multiply(exchangeRate).setScale(Utils.SCALE, RoundingMode.HALF_EVEN).stripTrailingZeros();

        return ExchangeResponse.builder()
                .currency(finalCurrency)
                .quantity(finalQuantity)
                .build();
    }
}
