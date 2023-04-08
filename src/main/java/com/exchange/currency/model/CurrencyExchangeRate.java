package com.exchange.currency.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class CurrencyExchangeRate {
    private String currency;

    private BigDecimal exchangeRate;
}
