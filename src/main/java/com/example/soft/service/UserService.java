package com.example.soft.service;

import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.UserDto;
import com.example.soft.entity.UserEntity;
import java.util.List;


public interface UserService {

    List<UserDto> findAllUsers();

    UserDto findById(long userId);

    List<CustomerDto> findAllByRoleCustomer();

    CustomerDto findCustomerById(long userId);

    UserEntity findByPhone(String username);

    UserDto createUser(UserDto userDto);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteUserById(long userId);
    UserDto updateUser(UserDto userDto);

}
