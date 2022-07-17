package com.example.eventdemo.web;

import com.example.eventdemo.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/create")
    public String createOrder(){
        this.orderService.createOrder("3", 33);
        return "Hello!";
    }
}
