package org.top.countrydictionaryapiapp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.top.countrydictionaryapiapp.api.message.ApiRecords.*;

@RestController
@RequestMapping("api")
public class ServerStatusController {

    @GetMapping
    public StringMessage serverStatus() {
        return new StringMessage("server is running");
    }

    @GetMapping("ping")
    public StringMessage ping() {
        return new StringMessage("pong");
    }
}
