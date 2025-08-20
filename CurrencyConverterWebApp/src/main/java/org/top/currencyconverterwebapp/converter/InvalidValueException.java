package org.top.currencyconverterwebapp.converter;

// некорректное значение для конвертации
public class InvalidValueException extends RuntimeException {
    public InvalidValueException(double value) {
        super("value '" + value + "' is invalid");
    }
}
