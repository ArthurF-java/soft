package com.example.soft.controllers;

import com.example.soft.dto.OrderDto;
import com.example.soft.entity.Order;
import com.example.soft.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{order_id}")
    public OrderDto findOrderById(@PathVariable String order_id){
        return orderService.findOrderByID(Long.parseLong(order_id));
    }
}
