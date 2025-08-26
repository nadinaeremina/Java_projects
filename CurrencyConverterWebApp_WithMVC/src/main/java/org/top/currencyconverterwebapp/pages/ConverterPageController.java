package org.top.currencyconverterwebapp.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.currencyconverterwebapp.converter.CurrencyConverter;

import java.util.List;

@Controller
@RequestMapping("converter")
public class ConverterPageController {

    private final CurrencyConverter converter;

    public ConverterPageController(CurrencyConverter converter) {
        this.converter = converter;
    }

    // этот обработчик для получения формы
    @GetMapping
    public String getConverterForm(Model model) {
        List<String> currencies = converter.supportedCurrencies();

        model.addAttribute("currencies", currencies);

        // установить значения исходных данных по умолчанию если это необходимо
        Object from = model.getAttribute("from");
        if (from == null) {
            model.addAttribute("from", currencies.getFirst());
        }

        Object to = model.getAttribute("to");
        if (to == null) {
            model.addAttribute("to", currencies.getFirst());
        }

        Object value = model.getAttribute("value");
        if (value == null) {
            model.addAttribute("value", 1);
        }

        Object result = model.getAttribute("result");
        if (result == null) {
            model.addAttribute("result", converter.convert(currencies.getFirst(), currencies.getFirst(), 1));
        }

        return "converter-form";
    }

    // этот обработчик для обработки (отправки) формы
    @PostMapping
    public String postConverterForm(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam Double value,
            RedirectAttributes ra
            // если использовать 'RedirectAttributes' вместо 'Model' - то можно сохранить
            // значение переменной 'result'
    ) {
        double result = converter.convert(from, to, value);

        // добавляем в 'RedirectAttributes' - 'FlashAttribute' - то 'result'
        // сохранится роно на один 'redirect' вперед
        ra.addFlashAttribute("result", result);
        ra.addFlashAttribute("from", from);
        ra.addFlashAttribute("to", to);
        ra.addFlashAttribute("value", value);

        // те и 'result' сохранился и get-запрос отработал, подтянул свои валюты
        return "redirect:/converter"; // перенаправление на getConverterForm
    }
}
