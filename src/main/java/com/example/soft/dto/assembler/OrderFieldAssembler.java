package com.example.soft.dto.assembler;

import com.example.soft.dto.OrderDto;
import com.example.soft.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderFieldAssembler {

    public Order assemblerFromOrderDtoToOrder(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setEmployeeId(order.getEmployeeId());
        order.setNeedInstallation(order.isNeedInstallation());
        order.setProductType(orderDto.getProductType());
        order.setAmount(orderDto.getAmount());
        order.setOrderTime(orderDto.getOrderTime());
        order.setExecutionTime(orderDto.getExecutionTime());
        order.setInstallTime(orderDto.getInstallTime());
        return order;
    }

    public OrderDto assemblerFromOrderToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setEmployeeId(order.getEmployeeId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setProductType(order.getProductType());
        orderDto.setNeedInstallation(order.isNeedInstallation());
        orderDto.setAmount(order.getAmount());
        orderDto.setOrderTime(order.getOrderTime());
        orderDto.setExecutionTime(order.getExecutionTime());
        orderDto.setInstallTime(order.getInstallTime());

        return orderDto;
    }
}
