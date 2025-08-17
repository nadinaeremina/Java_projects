package org.top.springpenprobe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller - это клас, который содержит методы http-обработчиков (handlers)

// помечаем аннотацией - покажет, что наш класс является сервисом-контроллером
// и что методы данного контроллера могут принимать и обрабатывать http-запросы
@RestController
public class StatusController {

    @GetMapping
    public String status() {
        return "server is running";
    }

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }
}
