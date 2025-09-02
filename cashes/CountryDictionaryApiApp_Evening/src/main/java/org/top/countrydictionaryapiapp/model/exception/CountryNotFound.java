package org.top.countrydictionaryapiapp.model.exception;

public class CountryNotFound extends RuntimeException {
    public CountryNotFound() {
        super("country is not found");
    }
}
