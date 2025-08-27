package org.top.numbersystemscalculatorwebappwithmvc.api;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.top.numbersystemscalculatorwebappwithmvc.calculator.InvalidValueException;
import org.top.numbersystemscalculatorwebappwithmvc.calculator.NumberSystemsCalculator;
import org.top.numbersystemscalculatorwebappwithmvc.calculator.UnsupportedNumberSystemsException;
import org.top.numbersystemscalculatorwebappwithmvc.simple.SimpleNumberSystemsCalculator;

import java.util.List;

@RestController
@RequestMapping("api/systems")
public class NumberSystemsController {

    private final NumberSystemsCalculator calculator;

    // есть такой @Bean:
    // public NumberSystemsCalculator calculator() {
    //      return new SimpleNumberSystemsCalculator(provider());
    // }

    // внедрили этот сервис через DI
    public NumberSystemsController(NumberSystemsCalculator calculator) {
        this.calculator = calculator;
    }

    // системы исчисления, поддерживаемые калькулятором
    @GetMapping
    public List<String> supportedNumbersSystems() {
        return calculator.supportedNumbersSystems();
    }

    // POST // http://localhost:8080/api/systems/calc
    //{
    //"from": "8",
    //"to": "10",
    //"value": 100
    //}

    // передача данных в теле запроса
    // '@RequestBody' - обязательно, иначе не считается
    @PostMapping("calc")
    public ApiMessages.CalculateResultMessage convert(@RequestBody ApiMessages.DataToCalculatorMessage data) {
        if (data.from() == null || data.from().isEmpty()) {
            throw new EmptyRequestDataException("from");
        }
        if (data.to() == null || data.to().isEmpty()) {
            throw new EmptyRequestDataException("to");
        }
        if (data.value() == null || data.value().isEmpty()) {
            throw new EmptyRequestDataException("value");
        }
        String result = calculator.calculate(data.from(), data.to(), data.value());
        return new ApiMessages.CalculateResultMessage(result, data);
    }
    // ответ
    // {
    // "result": 52066.666666666664,
    // "arg": {
    // "from": "USD",
    // "to": "KZT",
    // "value": 100.0
    //}

    // переопределим в нашем контроллере обработчики для Exception

    // указываем тип исключения в скобках и обьявляем обработчик, который будет это исключение обрабатывать
    @ExceptionHandler(EmptyRequestDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiMessages.ErrorMessage handleEmptyRequestDataException(EmptyRequestDataException ex) {
        return new ApiMessages.ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    // указываем тип исключения через параметр типа - аннотация
    // те если у нас в каком-либо обработчике вылетит exception InvalidValueException
    // то у нас отработает этот обработчик
    @ExceptionHandler(InvalidValueException.class)
    // явно указываем код статуса - чтоы не был 200
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiMessages.ErrorMessage handleInvalidValueException(InvalidValueException ex) {
        return new ApiMessages.ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(UnsupportedNumberSystemsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiMessages.ErrorMessage handleUnsupportedCurrencyException(UnsupportedNumberSystemsException ex) {
        return new ApiMessages.ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }
}
