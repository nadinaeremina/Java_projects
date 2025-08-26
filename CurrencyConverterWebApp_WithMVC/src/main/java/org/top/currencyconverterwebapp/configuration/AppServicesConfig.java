package org.top.currencyconverterwebapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.top.currencyconverterwebapp.converter.CurrencyConverter;
import org.top.currencyconverterwebapp.converter.RatesProvider;
import org.top.currencyconverterwebapp.simple.SimpleCurrencyConverter;
import org.top.currencyconverterwebapp.simple.SimpleRatesProvider;

@Configuration
public class AppServicesConfig {

    @Bean
    public RatesProvider provider() {
        return new SimpleRatesProvider();
    }

    @Bean
    public CurrencyConverter converter() {
        return new SimpleCurrencyConverter(provider());
    }
}
