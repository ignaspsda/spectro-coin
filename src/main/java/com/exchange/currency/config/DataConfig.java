package com.exchange.currency.config;

import com.exchange.currency.model.CurrencyExchangeRate;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Configuration
public class DataConfig {
    @Value("${path.to.exchange.rates.csv}")
    private String exchangeRatesCsv;

    @Bean
    public List<CurrencyExchangeRate> currencyExchangeRates() {
        try (Reader reader = new BufferedReader(new FileReader(exchangeRatesCsv))) {
            CsvToBean<CurrencyExchangeRate> csvReader = new CsvToBeanBuilder<CurrencyExchangeRate>(reader)
                    .withType(CurrencyExchangeRate.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            return csvReader.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public List<String> currencies() {
        return currencyExchangeRates().stream()
                .map(CurrencyExchangeRate::getCurrency)
                .toList();
    }
}
