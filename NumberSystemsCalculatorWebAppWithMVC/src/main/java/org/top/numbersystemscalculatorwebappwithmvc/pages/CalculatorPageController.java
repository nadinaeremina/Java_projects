package org.top.numbersystemscalculatorwebappwithmvc.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.numbersystemscalculatorwebappwithmvc.calculator.NumberSystemsCalculator;

import java.util.List;

@Controller
@RequestMapping("calculator")
public class CalculatorPageController {

    private final NumberSystemsCalculator calculator;

    // затащили наш интерфейс - инициализировали поле данного класса
    public CalculatorPageController(NumberSystemsCalculator calculator) {
        this.calculator = calculator;
    }

    // этот обработчик для получения формы
    @GetMapping
    public String getCalculateForm(Model model) {
        List<String> systems = calculator.supportedNumbersSystems();

        model.addAttribute("systems", systems);

        // установить значения исходных данных по умолчанию если это необходимо
        Object from = model.getAttribute("from");
        if (from == null) {
            model.addAttribute("from", systems.getFirst());
        }

        Object to = model.getAttribute("to");
        if (to == null) {
            model.addAttribute("to", systems.getFirst());
        }

        Object value = model.getAttribute("value");
        if (value == null) {
            model.addAttribute("value", 1);
        }

        Object result = model.getAttribute("result");
        if (result == null) {
            model.addAttribute("result", calculator.calculate(systems.getFirst(),
                    systems.getFirst(), "1"));
        }

        return "calculate-form";
    }

    // этот обработчик для обработки (отправки) формы
    @PostMapping
    public String postConverterForm(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String value,
            RedirectAttributes ra
            // если использовать 'RedirectAttributes' вместо 'Model' - то можно сохранить
            // значение переменной 'result'
    ) {
        String result = calculator.calculate(from, to, value);

        // добавляем в 'RedirectAttributes' - 'FlashAttribute' - то 'result'
        // сохранится роно на один 'redirect' вперед
        ra.addFlashAttribute("result", result);
        ra.addFlashAttribute("from", from);
        ra.addFlashAttribute("to", to);
        ra.addFlashAttribute("value", value);

        // те и 'result' сохранился и get-запрос отработал, подтянул свои валюты
        return "redirect:/calculator"; // перенаправление на getConverterForm
    }
}
