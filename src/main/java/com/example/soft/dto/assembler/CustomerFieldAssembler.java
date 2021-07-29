package com.example.soft.dto.assembler;

import com.example.soft.dto.CustomerDto;
import com.example.soft.entity.UserEntity;
import com.example.soft.entity.enumeracion.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerFieldAssembler {

    // Need stopped bean circle
    @Autowired
    private OrderFieldAssembler orderFieldAssembler;

    public UserEntity assemble(final CustomerDto customerDto) {
        UserEntity customer = null;
        if (customerDto != null) {
            customer = new UserEntity();
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setPhone(customerDto.getPhone());
            customer.setRole(Role.ROLE_CUSTOMER);
            customer.setEmail(customerDto.getEmail());
            customer.setCity(customerDto.getCity());
            customer.setStreet(customerDto.getStreet());
            customer.setHouse(customerDto.getHouse());
            customer.setFlat(customerDto.getFlat());

            if (customerDto.getOrderList() != null) {
                customer.setOrders(customerDto.getOrderList().stream()
                        .map(orderFieldAssembler::assemble)
                        .collect(Collectors.toList()));
            }
        }
        return customer;
    }

    public UserEntity assemble(final CustomerDto customerDto, final UserEntity existed) {
        UserEntity customer = null;
        if (customerDto != null) {
            customer = new UserEntity();
            customer.setId(customerDto.getId() != null ?
                    customerDto.getId() : existed.getId());
            customer.setFirstName(customerDto.getFirstName() != null ?
                    customerDto.getFirstName() : existed.getFirstName());
            customer.setLastName(customerDto.getLastName() != null ?
                    customerDto.getLastName() : existed.getLastName());
            customer.setPhone(customerDto.getPhone() != null ?
                    customerDto.getPhone() : existed.getPhone());
            customer.setRole(Role.ROLE_CUSTOMER);
            customer.setEmail(customerDto.getEmail() != null ?
                    customerDto.getEmail() : existed.getEmail());
            customer.setCity(customerDto.getCity() != null ?
                    customerDto.getCity() : existed.getCity());
            customer.setStreet(customerDto.getStreet() != null ?
                    customerDto.getStreet() : existed.getStreet());
            customer.setHouse(customerDto.getHouse() != null ?
                    customerDto.getHouse() : existed.getHouse());
            customer.setFlat(customerDto.getFlat() != null ?
                    customerDto.getFlat() : existed.getFlat());

            if (customerDto.getOrderList() != null) {
                customer.setOrders(customerDto.getOrderList().stream()
                        .map(orderFieldAssembler::assemble)
                        .collect(Collectors.toList()));
            }
        }
        return customer;
    }

    public CustomerDto disassemble(final UserEntity customer) {
        CustomerDto customerDto = null;
        if (customer != null) {
            customerDto = new CustomerDto();
            customerDto.setId(customer.getId());
            customerDto.setId(customer.getId());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setPhone(customer.getPhone());
            customerDto.setEmail(customer.getEmail());
            customerDto.setCity(customer.getCity());
            customerDto.setStreet(customer.getStreet());
            customerDto.setHouse(customer.getHouse());
            customerDto.setFlat(customer.getFlat());

            if (customer.getOrders() != null) {
                customerDto.setOrderList(customer.getOrders().stream()
                        .map(orderFieldAssembler::disassemble)
                        .collect(Collectors.toList()));
            }
        }
        return customerDto;
    }

    public CustomerDto disassembleForOrder(final UserEntity customer) {
        CustomerDto customerDto = null;
        if (customer != null) {
            customerDto = new CustomerDto();
            customerDto.setId(customer.getId());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setPhone(customer.getPhone());
            customerDto.setEmail(customer.getEmail());
        }
        return customerDto;
    }
}
