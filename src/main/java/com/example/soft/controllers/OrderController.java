package com.example.soft.controllers;

import com.example.soft.dto.OrderDto;
import com.example.soft.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/customer_id/{customer_id}")
    public List<OrderDto> findAllOrdersByCustomerId(@PathVariable long customer_id){
        return orderService.findAllOrdersByCustomerId(customer_id);
    }

    @GetMapping
    public List<OrderDto> findAllOrders(){
        return orderService.findAllOrders();
    }

    @GetMapping("/{order_id}")
    public OrderDto findOrderById(@PathVariable long order_id){
        return orderService.findOrderByID(order_id);
    }

    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto orderDto){
        return orderService.addOrder(orderDto);
    }

    @PutMapping
    public OrderDto editOrder(@RequestBody OrderDto orderDto){
        return orderService.editOrder(orderDto);
    }

    @DeleteMapping("/{order_id}")
    public String deleteOrder(@PathVariable long order_id ){
        return orderService.deleteOrderById(order_id);
    }



}

