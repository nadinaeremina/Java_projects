package org.top.numbersystemscalculatorwebappwithmvc.calculator;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(double value) {
        super("value '" + value + "' is invalid");
    }
}
