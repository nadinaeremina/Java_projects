package org.top.numbersystemscalculatorwebappwithmvc.api;

public class ApiMessages {

    // StringMessage - строковое сообщение
    public record StringMessage(String message) {}

    // DataToCalculatorMessage - данные для перевода значения из одной системы исчисления в другую
    public record DataToCalculatorMessage(String from, String to, String value) {}

    // CalculateResultMessage - результат перевода
    public record CalculateResultMessage(String result, DataToCalculatorMessage arg) {}

    // ErrorMessage - сообщение об ошибке
    public record ErrorMessage(String type, String message) {}
}
