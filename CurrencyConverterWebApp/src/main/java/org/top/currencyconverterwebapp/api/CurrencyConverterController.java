package org.top.currencyconverterwebapp.api;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.top.currencyconverterwebapp.converter.CurrencyConverter;
import org.top.currencyconverterwebapp.converter.InvalidValueException;
import org.top.currencyconverterwebapp.converter.UnsupportedCurrencyException;
import org.top.currencyconverterwebapp.simple.SimpleCurrencyConverter;
import org.top.currencyconverterwebapp.simple.SimpleRatesProvider;
import org.top.currencyconverterwebapp.api.ApiMessages.*;

import java.util.List;

@RestController
@RequestMapping("api/currencies")
public class CurrencyConverterController {

    private final CurrencyConverter converter;

    // есть такой @Bean
    // public CurrencyConverter converter() {
        // return new SimpleCurrencyConverter(provider());
    // }

    // внедрили сервис через DI
    public CurrencyConverterController(CurrencyConverter converter) {
        this.converter = converter;
    }

    // валюты, поддерживаемые конвертером
    @GetMapping
    public List<String> supportedCurrencies() {
        return converter.supportedCurrencies();
    }

    /*----------------------------*/
    /* ВАРИАНТ С ExceptionHandler */
    /*----------------------------*/

    // POST // http://localhost:8080/api/currencies/convert
    //{
        //"from": "USD",
        //"to": "KZT",
        //"value": 100
    //}

    // передача данных в теле запроса
    // '@RequestBody' - обязательно, иначе не считается
    @PostMapping("convert")
    public ConvertResultMessage convert(@RequestBody DataToConvertMessage data) {
        if (data.from() == null || data.from().isEmpty()) {
            throw new EmptyRequestDataException("from");
        }
        if (data.to() == null || data.to().isEmpty()) {
            throw new EmptyRequestDataException("to");
        }
        if (data.value() == null) {
            throw new EmptyRequestDataException("value");
        }
        double result = converter.convert(data.from(), data.to(), data.value());
        return new ConvertResultMessage(result, data);
    }
    // ответ
    // {
        // "result": 52066.666666666664,
            // "arg": {
            // "from": "USD",
            // "to": "KZT",
            // "value": 100.0
    //}

                                        /*--------------------------*/
                                        /* ВАРИАНТ С ResponseEntity */
                                        /*--------------------------*/

    // переопределим в нашем контроллере обработчики для Exception

    // указываем тип исключения в скобках и обьявляем обработчик, который будет это исключение обрабатывать
    @ExceptionHandler(EmptyRequestDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleEmptyRequestDataException(EmptyRequestDataException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    // указываем тип исключения через параметр типа - аннотация
    // те если у нас в каком-либо обработчике вылетит exception InvalidValueException
    // то у нас отработает этот обработчик
    @ExceptionHandler(InvalidValueException.class)
    // явно указываем код статуса - чтоы не был 200
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidValueException(InvalidValueException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(UnsupportedCurrencyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleUnsupportedCurrencyException(UnsupportedCurrencyException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

                                     /*--------------------------*/
                                     /* ВАРИАНТ С ResponseEntity */
                                     /*--------------------------*/

//    @PostMapping("convert")
//    public ResponseEntity<?> convert(@RequestBody DataToConvertMessage data) {
//        if (data.from() == null || data.from().isEmpty()) {
//            // 400
//            ErrorMessage error = new ErrorMessage("EmptyString", "'from' is null or empty");
//            return ResponseEntity.badRequest().body(error);
//        }
//        if (data.to() == null || data.to().isEmpty()) {
//            // 400
//            ErrorMessage error = new ErrorMessage("EmptyString", "'to' is null or empty");
//            return ResponseEntity.badRequest().body(error);
//        }
//        if (data.value() == null) {
//            // 400
//            ErrorMessage error = new ErrorMessage("EmptyValue", "'value' is null");
//            return ResponseEntity.badRequest().body(error);
//        }
//
//        try {
//            double result = converter.convert(data.from(), data.to(), data.value());
//            // 200
//            ConvertResultMessage resultMessage = new ConvertResultMessage(result, data);
//            return ResponseEntity.ok(resultMessage);
//        } catch (InvalidValueException ex) {
//            // 400
//            ErrorMessage error = new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
//            return ResponseEntity.badRequest().body(error);
//        } catch (UnsupportedCurrencyException ex) {
//            // 404
//            ErrorMessage error = new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//        }
//    }
}
