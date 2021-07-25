package com.example.soft.service;

import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.UserDto;
import com.example.soft.entity.User;
import com.example.soft.entity.enumeracion.Role;
import java.util.List;


public interface UserService {
    List<User> findAllUsers();
    UserDto findById(long userId);
    List<CustomerDto> findAllByRoleCustomer();
    List<User> findAllUsersByRole(Role role);
    CustomerDto findCustomerById(long userId);
    User findByPhone(String username);
    UserDto addUser(UserDto userDto);
    CustomerDto addCustomer(CustomerDto customerDto);
    CustomerDto editCustomer(CustomerDto customerDto);
    void deleteUser(long userId);
    UserDto editUser(UserDto userDto);

}
