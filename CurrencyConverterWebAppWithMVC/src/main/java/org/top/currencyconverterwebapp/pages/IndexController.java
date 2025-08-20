package org.top.currencyconverterwebapp.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;

// аннотация обязаельно '@Controller'
@Controller
public class IndexController {

    // возвращаемый результат - обязательно 'String'
    // тогда 'Spring' будет искать шаблон с таким именем
    @GetMapping
    public String index() {
        return "index";
    }
    // здесь возвращается имя шаблона = имя html-файла в папке "templates" без расщирения
    // 'index.html'

    @GetMapping("time")
    public String time(Model model) {
        // 'model' - модель страницы, мы можем в нее добавлять различные атрибуты и получать
        model.addAttribute("nowTime", LocalTime.now());
        // ключ - это имя атрибута, по которому мы можем получить в шаблоне
        // второй параметр - это значение, которое мы сохранили
        return "time";
    }
}
