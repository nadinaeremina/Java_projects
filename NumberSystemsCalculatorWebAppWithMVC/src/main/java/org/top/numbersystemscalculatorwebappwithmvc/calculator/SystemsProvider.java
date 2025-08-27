package org.top.numbersystemscalculatorwebappwithmvc.calculator;

import java.util.List;

// SystemsProvider - сервис для получения систем исчисления
public interface SystemsProvider {
    // GetSystems - получить список систем исчисления
    // вход:
    // выход: список систем исчисления
    List<String> GetSystems();
}
