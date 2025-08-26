package org.top.numbersystemscalculatorwebappwithmvc.calculator;

public class UnsupportedNumberSystemsException extends RuntimeException {
    public UnsupportedNumberSystemsException(String numbersSystemsCode) {
        super("NumbersSystems '" + numbersSystemsCode + "' is unsupported");
    }
}
