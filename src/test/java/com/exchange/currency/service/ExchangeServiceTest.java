package com.exchange.currency.service;

import com.exchange.currency.payload.ExchangeRequest;
import com.exchange.currency.payload.ExchangeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceTest {
    private ExchangeService exchangeService;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    void setUp() {
        exchangeService = new ExchangeService(currencyService, exchangeRateService);
    }

    @Test
    void exchangeCurrency_whenCurrenciesAreValid_shouldReturnExpectedExchangeResponse() {
        ExchangeRequest exchangeRequest = ExchangeRequest.builder()
                .initialCurrency("USD")
                .finalCurrency("EUR")
                .quantity(BigDecimal.valueOf(100))
                .build();

        Mockito.when(exchangeRateService.getExchangeRate("USD", "EUR")).thenReturn(BigDecimal.valueOf(0.81));
        ExchangeResponse expectedResponse = ExchangeResponse.builder()
                .currency("EUR")
                .quantity(BigDecimal.valueOf(81))
                .build();

        ExchangeResponse response = exchangeService.exchangeCurrency(exchangeRequest);

        assertEquals(expectedResponse, response);
    }
}