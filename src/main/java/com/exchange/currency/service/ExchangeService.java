package com.exchange.currency.service;

import com.exchange.currency.component.DataLoader;
import com.exchange.currency.model.CurrencyExchangeRate;
import com.exchange.currency.payload.ExchangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {
    private final DataLoader dataLoader;
    private final CurrencyService currencyService;

    @Autowired
    public ExchangeService(DataLoader dataLoader, CurrencyService currencyService) {
        this.dataLoader = dataLoader;
        this.currencyService = currencyService;
    }

    public String exchangeCurrency(ExchangeRequest exchangeRequest) {
        List<CurrencyExchangeRate> currencyExchangeRates = dataLoader.loadExchangeRates();

        List<String> currencyList = currencyExchangeRates.stream()
                .map(CurrencyExchangeRate::getCurrency)
                .toList();

        currencyService.currenciesAreValid(currencyList, exchangeRequest.getInitialCurrency(), exchangeRequest.getFinalCurrency());

        System.out.println("Currency exchange rate list: " + currencyExchangeRates);
        System.out.println("Exchange request: " + exchangeRequest);

        return "Test";
    }
}
