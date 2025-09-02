package org.top.countrydictionaryapiapp.memory;

import org.springframework.stereotype.Service;
import org.top.countrydictionaryapiapp.model.Country;
import org.top.countrydictionaryapiapp.model.CountryStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// TODO: inject component into spring application (внедрить компонент в приложение spring)
@Service
public class CountryCache implements CountryStorage {

    // кэшируемые данные, ключ - alpha2Code страны, значение - объект страны
    private final HashMap<String, Country> countries;

    // провайдер для работы с данными из хранилища
    private final CountryStorage cacheDataProvider;

    public CountryCache(CountryStorage cacheDataProvider) {
        countries = new HashMap<>();    // изначально кэш пуст
        this.cacheDataProvider = cacheDataProvider;
    }

    @Override
    public List<Country> selectAll() {
        // 1. получить список всех стран
        List<Country> allCountries = cacheDataProvider.selectAll();
        // 2. очистить старые данные в кэше
        countries.clear();
        // 3. заполнить данные в кэше
        for (Country country : allCountries) {
            // сохраняем в кэш глубокие копии объектов
            countries.put(country.getAlpha2Code(), new Country(country));
        }
        // 4. вернуть список стран
        return allCountries;
    }

    @Override
    public Optional<Country> selectByCode(String alpha2Code) {
        if (countries.containsKey(alpha2Code)) {
            return Optional.of(countries.get(alpha2Code));
        }
        Optional<Country> result = cacheDataProvider.selectByCode(alpha2Code);
        result.ifPresent(country ->
            countries.put(country.getAlpha2Code(), new Country(country))
        );
        return result;
    }

    @Override
    public void insert(Country country) {

    }

    @Override
    public void insertAll(List<Country> countries) {

    }

    @Override
    public void update(Country country) {
        cacheDataProvider.update(country);
        countries.remove(country.getAlpha2Code());
    }
}
