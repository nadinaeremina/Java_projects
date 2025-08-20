package org.top.currencyconverterwebapp.converter;

// валюта, которая не поддерживается
public class UnsupportedCurrencyException extends RuntimeException {
    public UnsupportedCurrencyException(String currencyCode) {
        super("currency '" + currencyCode + "' is unsupported");
    }
}
