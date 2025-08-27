package org.top.numbersystemscalculatorwebappwithmvc.simple;

import org.top.numbersystemscalculatorwebappwithmvc.calculator.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SimpleNumberSystemsCalculator - простой калькулятор систем исчисления
public class SimpleNumberSystemsCalculator implements NumberSystemsCalculator {

    private final List<String> systems;

    public SimpleNumberSystemsCalculator(SystemsProvider provaider) {
        systems = provaider.GetSystems();
    }

    @Override
    public List<String> supportedNumbersSystems() {
        return systems;
    }

    @Override
    public String calculate(String from, String to, String value) {
        if (Integer.parseInt(value) < 0) {
            throw new InvalidValueException(value);
        }
        if (!systems.contains(from)) {
            throw new UnsupportedNumberSystemsException(from);
        }
        if (!systems.contains(to)) {
            throw new UnsupportedNumberSystemsException(to);
        }
        int decimal = Integer.parseInt(value, Integer.parseInt(from));
        return Integer.toString(decimal, Integer.parseInt(to));
    }

}
