package com.exchange.currency.component;

import com.exchange.currency.model.CurrencyExchangeRate;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class DataLoader {
    @Value("${path.to.exchange.rates.csv}")
    private String exchangeRatesCsv;

    public List<CurrencyExchangeRate> loadExchangeRates() {
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
}
