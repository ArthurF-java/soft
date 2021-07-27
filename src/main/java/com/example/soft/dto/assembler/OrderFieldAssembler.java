package com.example.soft.dto.assembler;

import com.example.soft.dto.OrderDto;
import com.example.soft.entity.OrderEntity;
import com.example.soft.exeption_handing.users.UserNotFoundException;
import com.example.soft.repository.OrderRepository;
import com.example.soft.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderFieldAssembler {

    ProductDescriptionAssembler productDescriptionAssembler;
    OrderRepository orderRepository;
    UserRepository userRepository;

    public OrderEntity assemblerFromOrderDtoToOrder(OrderDto orderDto) {
        OrderEntity orderEntity = null;
        if (orderDto != null) {
            orderEntity = new OrderEntity();
            orderEntity.setId(orderDto.getId());
            orderEntity.setNeedInstallation(orderDto.isNeedInstallation());
            orderEntity.setProductType(orderDto.getProductType());
            orderEntity.setAmount(orderDto.getAmount());
            orderEntity.setOrderTime(orderDto.getOrderTime());
            orderEntity.setExecutionTime(orderDto.getExecutionTime());
            orderEntity.setInstallTime(orderDto.getInstallTime());

            orderEntity.setEmployee(userRepository.findById(orderDto.getEmployeeId()).orElse(null));
            if (orderEntity.getEmployee()==null){
                throw  new UserNotFoundException(
                        "Not found in Database employee with Id= " + orderDto.getCustomerId());
            }

            orderEntity.setCustomer(userRepository.findById(orderDto.getCustomerId()).orElse(null));
            if (orderEntity.getCustomer() == null) {
                throw new UserNotFoundException(
                        "Not found in Database customer with Id= " + orderDto.getCustomerId());
            }
            orderEntity.setDescriptionList(orderEntity.getId()==null? null :
                    orderRepository.existsById(orderEntity.getId())?
                    orderRepository.getById(orderDto.getId()).getDescriptionList():null);


        }
        return orderEntity;
    }

    public OrderDto assemblerFromOrderToOrderDto(OrderEntity orderEntity) {
        OrderDto orderDto = null;
        if (orderEntity != null) {
            orderDto = new OrderDto();
            orderDto.setId(orderEntity.getId());
            orderDto.setEmployeeId(orderEntity.getEmployee().getId());
            orderDto.setCustomerId(orderEntity.getCustomer().getId());
            orderDto.setProductType(orderEntity.getProductType());
            orderDto.setNeedInstallation(orderEntity.isNeedInstallation());
            orderDto.setAmount(orderEntity.getAmount());
            orderDto.setOrderTime(orderEntity.getOrderTime());
            orderDto.setExecutionTime(orderEntity.getExecutionTime());
            orderDto.setInstallTime(orderEntity.getInstallTime());
            orderDto.setDescriptionList(orderEntity.getDescriptionList()==null? null:
                    orderEntity.getDescriptionList()
            .stream().map(s->productDescriptionAssembler.assemblerFromEntityToDto(s))
            .collect(Collectors.toList()));
        }
        return orderDto;
    }
}
