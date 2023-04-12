package com.exchange.currency.payload;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ExchangeResponse {
    private final String currency;
    private final BigDecimal quantity;
}
