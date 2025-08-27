package org.top.numbersystemscalculatorwebappwithmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.top.numbersystemscalculatorwebappwithmvc.calculator.NumberSystemsCalculator;
import org.top.numbersystemscalculatorwebappwithmvc.calculator.SystemsProvider;
import org.top.numbersystemscalculatorwebappwithmvc.simple.SimpleNumberSystemsCalculator;
import org.top.numbersystemscalculatorwebappwithmvc.simple.SimpleNumberSystemsProvaider;

@Configuration
public class AppServicesConfig {
    // теперь мы сможем внедрять эти классы в контроллеры
    // классы можно даже не помечать специальной аннотацией

    @Bean
    public SystemsProvider provider() {
        return new SimpleNumberSystemsProvaider();
    }

    @Bean
    public NumberSystemsCalculator calculate() {
        return new SimpleNumberSystemsCalculator(provider());
    }
}