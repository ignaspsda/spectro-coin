package com.exchange.currency.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ExchangeRequest {
    private final String initialCurrency;

    private final String finalCurrency;

    private final BigDecimal quantity;
}
