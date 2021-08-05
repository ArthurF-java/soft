package com.example.soft.service;

import com.example.soft.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto findOrderById(long orderId);

    List <OrderDto> findAllOrders();

    List<OrderDto> findAllOrdersByCustomerId(long customerId);

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(OrderDto orderDto);

    void deleteOrderById(long orderId);

}
