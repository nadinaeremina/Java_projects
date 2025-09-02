package org.top.countrydictionaryapiapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.top.countrydictionaryapiapp.memory.CountryCache;
import org.top.countrydictionaryapiapp.model.CountryStorage;
import org.top.countrydictionaryapiapp.storage.CountryRepository;
import org.top.countrydictionaryapiapp.storage.RdbCountryStorage;

@Configuration
public class ServiceConfiguration {

    private final CountryRepository countryRepository;

    public ServiceConfiguration(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Bean
    public CountryStorage rdbStorage() {
        return new RdbCountryStorage(countryRepository);
    }

    @Bean
    public CountryStorage cacheDataProvider() {
        return rdbStorage();
    }

    @Bean
    public CountryStorage cachedStorage() {
        return new CountryCache(cacheDataProvider());
    }

    @Bean
    public CountryStorage storage() {
        return cachedStorage();
    }
}
