package com.example.soft.controllers;

import com.example.soft.dto.OrderDto;
import com.example.soft.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public List<OrderDto> findAllOrdersByCustomerId(@PathVariable final long id){
        return orderService.findAllOrdersByCustomerId(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public List<OrderDto> findAllOrders(){
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public OrderDto findOrderById(@PathVariable final long id){
        return orderService.findOrderById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public OrderDto CreateOrder(@RequestBody final OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public OrderDto updateOrder(@RequestBody final OrderDto orderDto){
        return orderService.updateOrder(orderDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public void deleteOrder(@PathVariable final long id){
        orderService.deleteOrderById(id);
    }
}

