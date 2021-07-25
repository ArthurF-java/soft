package com.example.soft.service;

import com.example.soft.dto.OrderDto;
import com.example.soft.entity.Order;

public interface OrderService {
    OrderDto findOrderByID(long orderId);
    Order findOrderByCustomerId(long customerId);
    Order addOrder(Order order);
    Order editOrder(Order order);
    void deleteOrder(long orderId);

}
