package org.top.numbersystemscalculatorwebappwithmvc.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    // здесь возвращается имя шаблона = имя html-файла в папке "templates" без расширения
    // 'index.html'
}
