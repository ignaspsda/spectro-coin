package com.exchange.currency.controller;

import com.exchange.currency.payload.ExchangeRequest;
import com.exchange.currency.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currency")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchange")
    public String testApi(@RequestBody ExchangeRequest exchangeRequest) {
        return exchangeService.exchangeCurrency(exchangeRequest);
    }
}
