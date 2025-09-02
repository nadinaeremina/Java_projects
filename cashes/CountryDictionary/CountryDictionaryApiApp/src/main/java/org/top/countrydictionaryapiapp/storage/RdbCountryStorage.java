package org.top.countrydictionaryapiapp.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.top.countrydictionaryapiapp.model.Country;
import org.top.countrydictionaryapiapp.model.CountryStorage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RdbCountryStorage implements CountryStorage {

    private final CountryRepository repository;

    public RdbCountryStorage(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Country> selectAll() {
        return repository.findAll().stream()
            .map(row -> new Country(
                row.getAlpha2Code(),
                row.getShortName(),
                row.getOfficialName()
            )).toList();
    }

    @Override
    public Optional<Country> selectByCode(String alpha2Code) {
        Optional<CountryRow> result = repository.findByAlpha2Code(alpha2Code);
        if (result.isEmpty()){
            return Optional.empty();
        }
        CountryRow row = result.get();
        Country country = new Country(row.getAlpha2Code(), row.getShortName(), row.getOfficialName());
        return Optional.of(country);
    }

    @Override
    public void insert(Country country) {
        throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public void insertAll(List<Country> countries) {
        Stream<CountryRow> rows = countries.stream().map(country -> {
            CountryRow row = new CountryRow();
            row.setAlpha2Code(country.getAlpha2Code());
            row.setShortName(country.getShortName());
            row.setOfficialName(country.getOfficialName());
            return row;
        });
        repository.saveAll(rows.toList());
    }

    @Override
    public void update(Country country) {
        throw new UnsupportedOperationException("implement me!");
    }
}
