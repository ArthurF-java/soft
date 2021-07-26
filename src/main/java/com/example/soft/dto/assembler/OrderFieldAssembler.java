package com.example.soft.dto.assembler;

import com.example.soft.dto.OrderDto;
import com.example.soft.entity.Order;
import com.example.soft.exeption_handing.users.UserNotFoundException;
import com.example.soft.repository.OrderRepository;
import com.example.soft.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderFieldAssembler {

    OrderRepository orderRepository;
    UserRepository userRepository;

    public Order assemblerFromOrderDtoToOrder(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setEmployeeId(orderDto.getEmployeeId());
        order.setNeedInstallation(orderDto.isNeedInstallation());
        order.setProductType(orderDto.getProductType());
        order.setAmount(orderDto.getAmount());
        order.setOrderTime(orderDto.getOrderTime());
        order.setExecutionTime(orderDto.getExecutionTime());
        order.setInstallTime(orderDto.getInstallTime());

//        order.setDescriptionList(orderRepository.getById(orderDto.getId())
//                .getDescriptionList());

        order.setUser(userRepository.findById(orderDto.getCustomerId()).orElse(null));
        if(order.getUser()==null){
            throw new UserNotFoundException(
                    "Not found in Database customer with Id= "+orderDto.getCustomerId());
        }
        return order;
    }

    public OrderDto assemblerFromOrderToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setEmployeeId(order.getEmployeeId());
        orderDto.setCustomerId(order.getUser().getId());
        orderDto.setProductType(order.getProductType());
        orderDto.setNeedInstallation(order.isNeedInstallation());
        orderDto.setAmount(order.getAmount());
        orderDto.setOrderTime(order.getOrderTime());
        orderDto.setExecutionTime(order.getExecutionTime());
        orderDto.setInstallTime(order.getInstallTime());
//        orderDto.setDescriptionList(order.getDescriptionList());
        return orderDto;
    }
}
