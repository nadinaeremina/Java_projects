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

    @PostMapping
    public String postConverterForm(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam Double value,
            RedirectAttributes ra
    ) {
        double result = converter.convert(from, to, value);

        ra.addFlashAttribute("result", result);
        ra.addFlashAttribute("from", from);
        ra.addFlashAttribute("to", to);
        ra.addFlashAttribute("value", value);

        return "redirect:/converter"; // перенаправление на getConverterForm
    }
}
