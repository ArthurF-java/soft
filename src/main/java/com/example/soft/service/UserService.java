package com.example.soft.service;

import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.UserDto;
import com.example.soft.entity.UserEntity;
import com.example.soft.entity.enumeracion.Role;
import java.util.List;


public interface UserService {

    List<UserDto> findAllUsers();
    UserDto findById(long userId);
    List<CustomerDto> findAllByRoleCustomer();
    List<UserEntity> findAllUsersByRole(Role role);
    CustomerDto findCustomerById(long userId);
    UserEntity findByPhone(String username);
    UserDto addUser(UserDto userDto);
    CustomerDto addCustomer(CustomerDto customerDto);
    CustomerDto editCustomer(CustomerDto customerDto);
    String deleteUserById(long userId);
    UserDto editUser(UserDto userDto);

}
