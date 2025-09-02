package org.top.countrydictionaryapiapp.memory;

import org.top.countrydictionaryapiapp.model.Country;
import org.top.countrydictionaryapiapp.model.CountryStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// TODO: inject component into spring application (внедрить компонент в приложение spring)
public class CountryCache implements CountryStorage {

    // кэшируемые данные, ключ - alpha2Code страны, значение - объект страны
    private final HashMap<String, Country> countries;

    // провайдер для работы с данными из хранилища
    private final CountryStorage storage;

    public CountryCache(CountryStorage storage) {
        countries = new HashMap<>();    // изначально кэш пуст
        this.storage = storage;
    }

    @Override
    public List<Country> selectAll() {
        // 1. получить список всех стран
        List<Country> allCountries = storage.selectAll();
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
        Optional<Country> result = storage.selectByCode(alpha2Code);
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
        storage.update(country);
        countries.remove(country.getAlpha2Code());
    }
}
