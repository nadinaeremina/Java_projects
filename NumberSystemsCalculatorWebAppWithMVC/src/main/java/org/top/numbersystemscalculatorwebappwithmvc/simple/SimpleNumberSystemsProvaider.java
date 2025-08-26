package org.top.numbersystemscalculatorwebappwithmvc.simple;

import org.top.numbersystemscalculatorwebappwithmvc.calculator.ExchangeNumberSystem;
import org.top.numbersystemscalculatorwebappwithmvc.calculator.NumberSystemsProvider;

import java.util.List;

// SimpleNumberSystemsProvaider - простая реализация провайдера систем исчисления
public class SimpleNumberSystemsProvaider implements NumberSystemsProvider {

    @Override
    public List<ExchangeNumberSystem> GetNumberSystems(String baseNumberSystem) {
        return List.of(
                new ExchangeNumberSystem("2", 2),
                new ExchangeNumberSystem("8", 78.1),
                new ExchangeNumberSystem("10", 1),
                new ExchangeNumberSystem("16", 0.15)
        );
    }
}