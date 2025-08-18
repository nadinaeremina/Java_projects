package org.top.homeworkhttprequests;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

