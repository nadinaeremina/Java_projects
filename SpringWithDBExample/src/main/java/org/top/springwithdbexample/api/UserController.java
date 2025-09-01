package org.top.springwithdbexample.api;

import org.springframework.web.bind.annotation.*;
import org.top.springwithdbexample.entity.Order;
import org.top.springwithdbexample.entity.User;
import org.top.springwithdbexample.repository.OrderRepository;
import org.top.springwithdbexample.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @GetMapping("{id}/orders")
    public List<Order> getUserOrders(@PathVariable Integer id) {
        return orderRepository.findAllByUserId(id);
    }

    @PostMapping
    public User addUser(@RequestParam String username) {
        User user = new User();
        user.setUsername(username);
        user.setRegisteredAt(new Date());
        userRepository.save(user);
        return user;
    }

    @GetMapping("search")
    public List<User> searchUsers(@RequestParam String pattern) {
        return userRepository.findAllByUsernameContaining(pattern);
    }
}
