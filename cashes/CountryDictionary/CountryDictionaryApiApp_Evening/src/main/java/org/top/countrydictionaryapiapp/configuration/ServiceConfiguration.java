package org.top.countrydictionaryapiapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.top.countrydictionaryapiapp.memory.CountryCache;
import org.top.countrydictionaryapiapp.model.CountryStorage;
import org.top.countrydictionaryapiapp.storage.RdbCountryStorage;

@Configuration
public class ServiceConfiguration {

    private final RdbCountryStorage rdbCountryStorage;

    public ServiceConfiguration(RdbCountryStorage rdbCountryStorage) {
        this.rdbCountryStorage = rdbCountryStorage;
    }

    @Bean
    public CountryCache countryCache() {
        return new CountryCache(cacheDataProvider());
    }

    @Bean
    public CountryStorage cacheDataProvider() {
        return rdbCountryStorage;
    }

    @Bean
    CountryStorage storage() {
        return countryCache();
    }
}
