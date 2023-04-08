package com.exchange.currency.payload;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequest {
    private final String initialCurrency;

    private final String finalCurrency;

    private final BigDecimal quantity;

    public ExchangeRequest(String initialCurrency, String finalCurrency, BigDecimal quantity) {
        this.initialCurrency = initialCurrency;
        this.finalCurrency = finalCurrency;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ExchangeRequest{" +
                "initialCurrency=" + initialCurrency +
                ", finalCurrency=" + finalCurrency +
                ", quantity=" + quantity +
                '}';
    }
}
