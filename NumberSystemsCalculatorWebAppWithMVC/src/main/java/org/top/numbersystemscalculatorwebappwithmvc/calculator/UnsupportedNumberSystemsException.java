package org.top.numbersystemscalculatorwebappwithmvc.calculator;

// такая система исчисления не поддерживается
public class UnsupportedNumberSystemsException extends RuntimeException {
    public UnsupportedNumberSystemsException(String numbersSystemsCode) {
        super("NumbersSystems '" + numbersSystemsCode + "' is unsupported");
    }
}
