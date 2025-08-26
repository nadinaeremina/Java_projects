package org.top.currencyconverterwebapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesWithJavaConfig {
    @Value("${rates.provider}")
    private String providerName;
    public String getProviderName() {
        return providerName;
    }
}
