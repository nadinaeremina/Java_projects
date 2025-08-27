package org.top.numbersystemscalculatorwebappwithmvc.calculator;

// невалидное число (отрицательное0
public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String value) {
        super("value '" + value + "' is invalid");
    }
}
