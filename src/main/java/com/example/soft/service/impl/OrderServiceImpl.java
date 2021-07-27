package com.example.soft.service.impl;

import com.example.soft.dto.OrderDto;
import com.example.soft.dto.assembler.OrderFieldAssembler;
import com.example.soft.entity.OrderEntity;
import com.example.soft.entity.UserEntity;
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
    private final static String CUSTOMER_NOT_FOUND_MESSAGE = "Not found in Database customer with Id= ";

    private final OrderFieldAssembler orderFieldAssembler;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto findOrderByID(long orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElse(null);
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
        UserEntity user = userRepository.findById(customerId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE + customerId);
        }
        return user.getOrders().stream()
                .map(orderFieldAssembler::assemblerFromOrderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        if(!userRepository.existsById(orderDto.getCustomerId())){
            throw new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE+orderDto.getCustomerId());
        }
        orderDto.setOrderTime(LocalDate.now());
        OrderEntity order = orderFieldAssembler.assemblerFromOrderDtoToOrder(orderDto);
        orderRepository.save(order);
        return orderFieldAssembler.assemblerFromOrderToOrderDto(order);
    }

    @Override
    public OrderDto editOrder(OrderDto orderDto) {
        if (!orderRepository.existsById(orderDto.getId())) {
            throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderDto.getId());
        }
        if(!userRepository.existsById(orderDto.getCustomerId())){
            throw new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE+orderDto.getCustomerId());
        }
        OrderEntity orderEntity = orderFieldAssembler.assemblerFromOrderDtoToOrder(orderDto);
        orderRepository.save(orderEntity);
        return orderFieldAssembler.assemblerFromOrderToOrderDto(orderEntity);
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
