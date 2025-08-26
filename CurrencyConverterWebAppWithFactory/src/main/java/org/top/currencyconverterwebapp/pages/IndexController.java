package org.top.currencyconverterwebapp.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("time")
    public String time(Model model) {
        model.addAttribute("nowTime", LocalTime.now());
        return "time";
    }
}
