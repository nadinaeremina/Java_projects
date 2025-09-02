package org.top.countrydictionaryapiapp.memory;

import org.springframework.stereotype.Service;
import org.top.countrydictionaryapiapp.model.Country;
import org.top.countrydictionaryapiapp.model.CountryStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CountryCache implements CountryStorage {

    // закэшированные данные
    // ключ - alpha2Code страны
    // значение - закэшированный объект
    // изначально пуст
    private final HashMap<String, Country> cache = new HashMap<>();

    // провайдер для работы с данными их храналища
    private final CountryStorage cacheDataProvider;

    public CountryCache(final CountryStorage cacheDataProvider) {
        this.cacheDataProvider = cacheDataProvider;
    }

    @Override
    public List<Country> selectAll() {
        // получим все страны
        List<Country> countries = cacheDataProvider.selectAll();
        // если все успешно получилось - то очистим кэш
        cache.clear();
        // и перезаполним его полученными данными
        for (Country country : countries) {
            cache.put(country.getAlpha2Code(), new Country(country)); // добавляем в кэш КОПИЮ ОБЪЕКТА!
        }
        // вернуть результат
        return countries;
    }

    @Override
    public Optional<Country> selectByCode(String alpha2Code) {
        if (cache.containsKey(alpha2Code)) {
            // если в кэше есть данный код, то вернуть копию объекта из кэша
            Country result = new Country(cache.get(alpha2Code));
            return Optional.of(result);
        }
        // иначе попробовать найти страну в хранилище
        Optional<Country> country = cacheDataProvider.selectByCode(alpha2Code);
        if (country.isEmpty()) {
            // не нашлось такой страны
            return Optional.empty();
        }
        // если нашлась - то закэшировать и вернуть результат
        cache.put(alpha2Code, new Country(country.get()));
        return country;
    }

    @Override
    public void insert(Country country) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public void insertAll(List<Country> countries) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public void update(Country country) {
        throw new UnsupportedOperationException("implement me");
    }
}
