package org.top.countrydictionaryapiapp.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryRow, Integer> {
    Optional<CountryRow> findByAlpha2Code(String alpha2Code);
}
