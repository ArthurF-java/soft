package com.example.soft.service.impl;

import com.example.soft.dto.OrderDto;
import com.example.soft.dto.assembler.OrderFieldAssembler;
import com.example.soft.entity.OrderEntity;
import com.example.soft.entity.UserEntity;
import com.example.soft.exeption.OrderNotFoundException;
import com.example.soft.exeption.UserNotFoundException;
import com.example.soft.repository.OrderRepository;
import com.example.soft.repository.UserRepository;
import com.example.soft.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderFieldAssembler orderFieldAssembler;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final static String ORDER_NOT_FOUND_MESSAGE = "Not found in Database order with Id = ";
    private final static String CUSTOMER_NOT_FOUND_MESSAGE = "Not found in Database customer with Id = ";

    @Override
    public OrderDto findOrderById(final long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderId));
        return orderFieldAssembler.disassemble(order);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream().map(orderFieldAssembler::disassemble)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllOrdersByCustomerId(final long customerId) {
        UserEntity customer = userRepository.findById(customerId)
                .orElseThrow(() -> new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE + customerId));
        return orderRepository.findAllByCustomer(customer).stream().map(orderFieldAssembler::disassemble)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto createOrder(final OrderDto orderDto) {
        UserEntity customer = userRepository.findById(orderDto.getCustomer().getId())
                .orElseThrow(() -> new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE + orderDto.getCustomer().getId()));
        UserEntity employee = userRepository.findById(orderDto.getEmployee().getId())
                .orElseThrow(() -> new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE + orderDto.getEmployee().getId()));
        OrderEntity order = orderFieldAssembler.assemble(orderDto);
        order.setOrderTime(LocalDate.now());
        order.setCustomer(customer);
        order.setEmployee(employee);
        orderRepository.save(order);
        return orderFieldAssembler.disassemble(order);
    }

    @Override
    public OrderDto updateOrder(final OrderDto orderDto) {
        OrderEntity existed = orderRepository.findById(orderDto.getId())
                .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderDto.getId()));
        UserEntity customer = userRepository.findById(orderDto.getCustomer().getId())
                .orElseThrow(() -> new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE + orderDto.getCustomer().getId()));
        UserEntity employee = userRepository.findById(orderDto.getEmployee().getId())
                .orElseThrow(() -> new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE + orderDto.getEmployee().getId()));
        OrderEntity orderEntity = orderFieldAssembler.assemble(orderDto, existed, customer, employee);
        orderRepository.save(orderEntity);
        return orderFieldAssembler.disassemble(orderEntity);
    }

    @Override
    public void deleteOrderById(final long orderId) {
        orderRepository.deleteById(orderId);
    }
}
