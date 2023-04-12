package com.exchange.currency.controller;

import com.exchange.currency.model.CurrencyExchangeRate;
import com.exchange.currency.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public List<CurrencyExchangeRate> getCurrencyExchangeRates() {
        return exchangeRateService.getAllCurrencyExchangeRates();
    }
}
