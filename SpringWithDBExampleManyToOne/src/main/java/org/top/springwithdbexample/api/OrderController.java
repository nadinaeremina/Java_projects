package org.top.springwithdbexample.api;

import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;
import org.top.springwithdbexample.entity.Order;
import org.top.springwithdbexample.entity.User;
import org.top.springwithdbexample.repository.OrderRepository;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public Order create(@RequestBody OrderCreateData orderCreateData) {
        // заполним данные заказа
        Order order = new Order();
        order.setDescription(orderCreateData.getDescription());
        order.setCreatedAt(new Date());
        // для пользователя заполним только id и установим данного пользователя в заказ
        User orderUser = new User();
        orderUser.setId(orderCreateData.getUserId());
        order.setUser(orderUser);
        // сохранить в БД
        orderRepository.save(order);
        // вернуть созданный заказ
        return order;
    }

    // получение заказа по id
    @GetMapping("{id}")
    public Optional<Order> getOrderById(@PathVariable Integer id) {
        return orderRepository.findById(id);
    }
}
