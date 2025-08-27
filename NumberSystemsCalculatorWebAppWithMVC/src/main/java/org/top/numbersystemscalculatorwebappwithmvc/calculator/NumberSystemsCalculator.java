package org.top.numbersystemscalculatorwebappwithmvc.calculator;

import java.util.List;

// NumberSystemsCalculator - калькулятор систем исчисления - интерфейс
public interface NumberSystemsCalculator {
    // supportedNumbersSystems - метод получения поддерживаемых систем исчисления
    // вход: -
    // выход: массив с видами систем исчисления
    // исключения: -
    List<String> supportedNumbersSystems();

    // calculate - метод перевода числа из одной системы исчисления в другую
    // вход:
    //  - from - код исходной системы исчисления
    //  - to - код целевой системы исчисления
    //  - value - значение для вычисления
    // выход: вычисленное значение
    // исключения: UnsupportedNumberSystemsException, InvalidValueException
    String calculate(String from, String to, String value);
}
