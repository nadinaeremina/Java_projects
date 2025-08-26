package org.top.numbersystemscalculatorwebappwithmvc.calculator;

import java.util.List;

// NumberSystemsCalculator - калькулятор систем исчисления - интерфейс
public interface NumberSystemsCalculator {
    // supportedNumbersSystems - метод получения поддерживаемых систем исчисления
    // вход: -
    // выход: массив с кодами систем исчисления
    // исключения: -
    List<String> supportedNumbersSystems();

    // calculate - метод конвертации значения одной валюты в другую
    // вход:
    //  - from - код исходной системы исчисления
    //  - to - код целевой системы исчисления
    //  - value - значение для вычисления
    // выход: вычисленное значение
    // исключения: UnsupportedNumberSystemsException, InvalidValueException
    double calculate(String from, String to, double value);
}
