package org.top.springwithdbexample.api;

import org.springframework.web.bind.annotation.*;
import org.top.springwithdbexample.entity.User;
import org.top.springwithdbexample.repository.UserRepository;

import java.util.*;

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
