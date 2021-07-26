package com.example.soft.service.impl;

import com.example.soft.dto.OrderDto;
import com.example.soft.dto.assembler.OrderFieldAssembler;
import com.example.soft.entity.Order;
import com.example.soft.entity.User;
import com.example.soft.exeption_handing.orders.OrderNotFoundException;
import com.example.soft.exeption_handing.users.UserNotFoundException;
import com.example.soft.repository.OrderRepository;
import com.example.soft.repository.UserRepository;
import com.example.soft.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor

public class OrderServiceImpl implements OrderService {

    private final static String ORDER_NOT_FOUND_MESSAGE = "Not found in Database order with Id= ";
    private final static String ORDER_EXIST_MESSAGE = "User already exist ind Database with Id= ";

    private final OrderFieldAssembler orderFieldAssembler;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto findOrderByID(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderId);
        }
        return orderFieldAssembler.assemblerFromOrderToOrderDto(order);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream().map(orderFieldAssembler::assemblerFromOrderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllOrdersByCustomerId(long customerId) {
        User user = userRepository.findById(customerId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Not found in Database order with Id= " + customerId);
        }
        return user.getOrderList().stream()
                .map(orderFieldAssembler::assemblerFromOrderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        orderDto.setOrderTime(LocalDate.now());
        Order order = orderFieldAssembler.assemblerFromOrderDtoToOrder(orderDto);
        orderRepository.save(order);
        return orderFieldAssembler.assemblerFromOrderToOrderDto(order);
    }

    @Override
    public OrderDto editOrder(OrderDto orderDto) {
        if (!orderRepository.existsById(orderDto.getId())) {
            throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderDto.getId());
        }
        Order order = orderFieldAssembler.assemblerFromOrderDtoToOrder(orderDto);
        orderRepository.save(order);
        return orderFieldAssembler.assemblerFromOrderToOrderDto(order);
    }

    @Override
    public String deleteOrderById(long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new UserNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderId);
        }
        orderRepository.deleteById(orderId);
        return "order with Id = " + orderId + " was deleted";
    }
}
