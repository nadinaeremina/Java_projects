package org.top.springwithdbhospital.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
    public ApplicationController() {}

    @GetMapping
    public String serverStatus() {
        return "server is running";
    }

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }
}
