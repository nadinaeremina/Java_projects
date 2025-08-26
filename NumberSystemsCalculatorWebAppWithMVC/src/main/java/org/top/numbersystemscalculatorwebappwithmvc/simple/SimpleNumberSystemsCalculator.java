package org.top.numbersystemscalculatorwebappwithmvc.simple;

import org.top.numbersystemscalculatorwebappwithmvc.calculator.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SimpleNumberSystemsCalculator - простой калькулятор систем исчисления
public class SimpleNumberSystemsCalculator implements NumberSystemsCalculator {
    private final Map<String, Double> rates;

    public SimpleNumberSystemsCalculator(NumberSystemsProvider provaider) {
        List<ExchangeNumberSystem> exchangeNumberSystems = provaider.GetNumberSystems("10");
        rates = new HashMap<>();
        for (ExchangeNumberSystem exchangeNumberSystem : exchangeNumberSystems) {
            rates.put(exchangeNumberSystem.code(), exchangeNumberSystem.rate());
        }
    }

    @Override
    public List<String> supportedNumbersSystems() {
        return rates.keySet().stream().toList();
    }

    @Override
    public double calculate(String from, String to, double value) {
        if (value < 0) {
            throw new InvalidValueException(value);
        }
        if (!rates.containsKey(from)) {
            throw new UnsupportedNumberSystemsException(from);
        }
        if (!rates.containsKey(to)) {
            throw new UnsupportedNumberSystemsException(to);
        }
        return value * rates.get(from) / rates.get(to);
    }
}
