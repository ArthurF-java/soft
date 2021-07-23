package com.example.soft.service;

import com.example.soft.dto.CustomerDto;
import com.example.soft.entity.User;
import com.example.soft.entity.enumeracion.Role;
import java.util.List;


public interface UserService {
    List<User> findAllUsers();
    User findById(long userId);
    List<CustomerDto> findAllByRoleCustomer();
    List<User> findAllUsersByRole(Role role);
    CustomerDto findCustomerById(long userId);
    User findByPhone(String username);
    User addUser(User user);
    CustomerDto addCustomer(CustomerDto customerDto);
    CustomerDto editCustomer(CustomerDto customerDto);
    void deleteUser(long userId);
    User editUser(User user);

}
