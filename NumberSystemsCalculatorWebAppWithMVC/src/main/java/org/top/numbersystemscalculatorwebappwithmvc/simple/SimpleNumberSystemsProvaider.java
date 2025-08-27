package org.top.numbersystemscalculatorwebappwithmvc.simple;

import org.top.numbersystemscalculatorwebappwithmvc.calculator.SystemsProvider;

import java.util.List;

// NumberSystemsProvider - простая реализация провайдера систем исчисления
public class SimpleNumberSystemsProvaider implements SystemsProvider {

    @Override
    public List<String> GetSystems() {
        return List.of("2", "8", "10", "16");
    }
}