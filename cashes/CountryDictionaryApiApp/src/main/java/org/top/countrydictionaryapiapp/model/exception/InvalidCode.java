package org.top.countrydictionaryapiapp.model.exception;

public class InvalidCode extends RuntimeException {
    public InvalidCode() {
        super("code is invalid");
    }
}
