package com.example.soft.service.impl;

import com.example.soft.dto.OrderDto;
import com.example.soft.dto.assembler.OrderFieldAssembler;
import com.example.soft.entity.Order;
import com.example.soft.exeption_handing.orders.OrderNotFoundException;
import com.example.soft.repository.OrderRepository;
import com.example.soft.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final static String ORDER_NOT_FOUND_MESSAGE = "Not found in Database order with Id= ";
    private final static String ORDER_EXIST_MESSAGE = "User already exist ind Database with Id= ";

    private final OrderFieldAssembler orderFieldAssembler;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto findOrderByID(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null){
            throw  new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE+orderId);
        }
        return orderFieldAssembler.assemblerFromOrderToOrderDto(order);
    }

    @Override
    public Order findOrderByCustomerId(long customerId) {
        return null;
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order editOrder(Order order) {
        if(orderRepository.existsById(order.getId())){
            throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE+order.getId());
        }
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long orderId) {

    }
}
