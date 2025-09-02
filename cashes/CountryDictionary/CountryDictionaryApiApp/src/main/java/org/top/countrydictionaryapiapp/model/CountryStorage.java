package org.top.countrydictionaryapiapp.model;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// CountryStorage - хранилище стран
@Service
public interface CountryStorage {
    List<Country> selectAll();
    Optional<Country> selectByCode(String alpha2Code);
    void insert(Country country);
    void insertAll(List<Country> countries);
    void update(Country country);
}
