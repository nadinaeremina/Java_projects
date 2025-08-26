package org.top.springwithdbexample;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ApplicationController {

    private final UserRepository userRepository;

    public ApplicationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String serverStatus() {
        return "server is running";
    }

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("user")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("user/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping("user/new")
    public User addUser(@RequestParam String username) {
        User user = new User();
        user.setUsername(username);
        user.setRegisteredAt(new Date());
        userRepository.save(user);
        return user;
    }

    @GetMapping("user/search")
    public List<User> searchUsers(@RequestParam String pattern) {
        // ЗАДАЧА: выполнить поиск всех пользователей по схеме nickname.icontains(pattern)
        return userRepository.findAllByUsernameContaining(pattern);
    }
}
