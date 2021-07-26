package com.example.soft.service;

import com.example.soft.dto.OrderDto;
import com.example.soft.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDto findOrderByID(long orderId);
    List <OrderDto> findAllOrders();
    List<OrderDto> findAllOrdersByCustomerId(long customerId);
    OrderDto addOrder(OrderDto orderDto);
    OrderDto editOrder(OrderDto orderDto);
    String deleteOrderById(long orderId);

}
