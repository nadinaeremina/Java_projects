package org.top.countrydictionaryapiapp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.top.countrydictionaryapiapp.model.Country;
import org.top.countrydictionaryapiapp.model.CountryDictionary;

import java.util.List;

@RestController
@RequestMapping("api/country-dictionary")
public class CountryDictionaryController {

    private final CountryDictionary dictionary;

    public CountryDictionaryController(CountryDictionary dictionary) {
        this.dictionary = dictionary;
    }

    @GetMapping
    public List<Country> getAll() {
        return dictionary.listAll();
    }

    @GetMapping("{code}")
    public Country getByCode(@PathVariable("code") String code) {
        return dictionary.getByCode(code);
    }
}
