package org.top.currencyconverterwebapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.top.currencyconverterwebapp.converter.CurrencyConverter;
import org.top.currencyconverterwebapp.converter.RatesProvider;
import org.top.currencyconverterwebapp.simple.SecondSimpleRatesProvider;
import org.top.currencyconverterwebapp.simple.SimpleCurrencyConverter;
import org.top.currencyconverterwebapp.simple.SimpleRatesProvider;

@Configuration
public class AppServicesConfig {

    private final RatesProviderFactory factory;

    public AppServicesConfig(RatesProviderFactory ratesProviderFactory) {
        factory = ratesProviderFactory;
    }

    @Bean
    public RatesProvider provider() {
        return factory.getRatesProviderUsingAppProperties();
    }

    @Bean
    public CurrencyConverter converter() {
        return new SimpleCurrencyConverter(provider());
    }
}
