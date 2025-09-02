package org.top.countrydictionaryapiapp.model.exception;

public class InvalidCountry extends RuntimeException {
    public InvalidCountry() {
        super("country is invalid");
    }
}
