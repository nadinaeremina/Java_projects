package org.top.currencyconverterwebapp.simple;

import org.top.currencyconverterwebapp.converter.ExchangeRate;
import org.top.currencyconverterwebapp.converter.RatesProvider;

import java.util.List;

public class SecondSimpleRatesProvider implements RatesProvider {
    @Override
    public List<ExchangeRate> getRates(String baseCurrency) {
        return List.of(
                new ExchangeRate("RUB", 1),
                new ExchangeRate("AZN", 47.51),
                new ExchangeRate("TRY", 0.51)
        );
    }
}
