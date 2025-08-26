package org.top.numbersystemscalculatorwebappwithmvc.calculator;

import java.util.List;

// NumberSystemsProvider - сервис для получения систем исчисления
public interface NumberSystemsProvider {
    // GetNumberSystems - получить список систем исчисления относительно базовой системы
    // вход: код базовой системы исчисления (по отношению к которой будут получены системы)
    // выход: список систем исчисления по отношению к базовой системе
    List<ExchangeNumberSystem> GetNumberSystems(String baseNumberSystem);
}

