package com.example.soft.dto.assembler;

import com.example.soft.dto.OrderDto;
import com.example.soft.entity.OrderEntity;
import com.example.soft.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderFieldAssembler {

    private final ProductDescriptionAssembler productDescriptionAssembler;
    private final UserFieldAssembler userFieldAssembler;
    private final CustomerFieldAssembler customerFieldAssembler;

    public OrderEntity assemble(final OrderDto orderDto) {
        OrderEntity orderEntity = null;
        if (orderDto != null) {
            orderEntity = new OrderEntity();
            orderEntity.setEmployee(userFieldAssembler.assemble(orderDto.getEmployee()));
            orderEntity.setCustomer(customerFieldAssembler.assemble(orderDto.getCustomer()));
            orderEntity.setIsNeedInstallation(orderDto.getIsNeedInstallation());
            orderEntity.setProductType(orderDto.getProductType());
            orderEntity.setAmount(orderDto.getAmount());
            orderEntity.setExecutionTime(orderDto.getExecutionTime());
            orderEntity.setInstallTime(orderDto.getInstallTime());

            if (orderDto.getDescriptionList() != null) {
                OrderEntity finalOrderEntity = orderEntity;
                orderEntity.setDescriptionList(orderDto.getDescriptionList()
                        .stream().map(s -> productDescriptionAssembler.assemble(s, finalOrderEntity))
                        .collect(Collectors.toList()));
            }
        }
        return orderEntity;
    }

    public OrderEntity assemble(final OrderDto orderDto, final OrderEntity existed,
                                final UserEntity customer, final UserEntity employee) {
        OrderEntity orderEntity = null;
        if (orderDto != null) {
            orderEntity = new OrderEntity();
            orderEntity.setEmployee(orderDto.getEmployee() != null ?
                    userFieldAssembler.assemble(orderDto.getEmployee(), employee) : employee);
            orderEntity.setId(orderDto.getId() != null ? orderDto.getId() : existed.getId());
            orderEntity.setCustomer(orderDto.getCustomer() != null ?
                    customerFieldAssembler.assemble(orderDto.getCustomer(), customer) : customer);
            orderEntity.setIsNeedInstallation(orderDto.getIsNeedInstallation() != null ?
                    orderDto.getIsNeedInstallation() : existed.getIsNeedInstallation());
            orderEntity.setProductType(orderDto.getProductType() != null ?
                    orderDto.getProductType() : existed.getProductType());
            orderEntity.setAmount(orderDto.getAmount() != null ?
                    orderDto.getAmount() : existed.getAmount());
            orderEntity.setExecutionTime(orderDto.getExecutionTime() != null ?
                    orderDto.getExecutionTime() : existed.getExecutionTime());
            orderEntity.setInstallTime(orderDto.getInstallTime() != null ?
                    orderDto.getInstallTime() : existed.getInstallTime());

            if (orderDto.getDescriptionList() != null) {
                OrderEntity finalOrderEntity = orderEntity;
                orderEntity.setDescriptionList(orderDto.getDescriptionList().stream().map(
                        (s) -> productDescriptionAssembler.assemble(s, finalOrderEntity))
                        .collect(Collectors.toList()));
            }
        }
        return orderEntity;
    }

    public OrderDto disassemble(final OrderEntity orderEntity) {
        OrderDto orderDto = null;
        if (orderEntity != null) {
            orderDto = new OrderDto();
            orderDto.setId(orderEntity.getId());
            orderDto.setEmployee(userFieldAssembler.disassembleForOrder(orderEntity.getEmployee()));
            orderDto.setCustomer(customerFieldAssembler.disassembleForOrder(orderEntity.getCustomer()));
            orderDto.setProductType(orderEntity.getProductType());
            orderDto.setIsNeedInstallation(orderEntity.getIsNeedInstallation());
            orderDto.setAmount(orderEntity.getAmount());
            orderDto.setOrderTime(orderEntity.getOrderTime());
            orderDto.setExecutionTime(orderEntity.getExecutionTime());
            orderDto.setInstallTime(orderEntity.getInstallTime());

            if (orderEntity.getDescriptionList() != null) {
                orderDto.setDescriptionList(orderEntity.getDescriptionList()
                        .stream().map(productDescriptionAssembler::disassemble)
                        .collect(Collectors.toList()));
            }
        }
        return orderDto;
    }
}
