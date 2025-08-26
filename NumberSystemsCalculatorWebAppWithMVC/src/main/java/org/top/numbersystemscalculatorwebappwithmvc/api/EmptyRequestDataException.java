package org.top.numbersystemscalculatorwebappwithmvc.api;

public class EmptyRequestDataException extends RuntimeException {
    public EmptyRequestDataException(String message) {
        super(message);
    }
}
