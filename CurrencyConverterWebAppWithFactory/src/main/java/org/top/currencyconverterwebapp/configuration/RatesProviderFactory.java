package org.top.currencyconverterwebapp.configuration;

import org.springframework.stereotype.Service;
import org.top.currencyconverterwebapp.converter.RatesProvider;
import org.top.currencyconverterwebapp.simple.SecondSimpleRatesProvider;
import org.top.currencyconverterwebapp.simple.SimpleRatesProvider;

import javax.naming.ConfigurationException;

// RatesProviderFactory - фабрика для создания объектов RatesProvider
@Service
public class RatesProviderFactory {

    private final PropertiesWithJavaConfig config;

    public RatesProviderFactory(PropertiesWithJavaConfig propertiesWithJavaConfig) {
        config = propertiesWithJavaConfig;
    }

    public RatesProvider getRatesProviderUsingAppProperties() {
        switch (config.getProviderName()) {
            case "simple":
                return new SimpleRatesProvider();
            case "second":
                return new SecondSimpleRatesProvider();
            default:
                throw new IllegalArgumentException("unknown provider: " + config.getProviderName());
        }
    }
}
