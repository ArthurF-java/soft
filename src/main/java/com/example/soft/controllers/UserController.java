package com.example.soft.controllers;


import com.example.soft.dto.CustomerDto;

import com.example.soft.entity.User;
import com.example.soft.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class UserController {

    UserService userService;

    @GetMapping("customers")
    public List<CustomerDto> getAllCustomers(){
        return userService.findAllByRoleCustomer();
    }
    @GetMapping("customers/{customer_id}")
    public CustomerDto findCustomerById(@PathVariable String customer_id){
        return userService.findCustomerById(Long.parseLong(customer_id));

    }
    @PostMapping("customers")
    public CustomerDto addUser(@RequestBody CustomerDto customerDto){
           return userService.addCustomer(customerDto);
    }

    @PutMapping("customers")
    public CustomerDto editUser(@RequestBody CustomerDto customerDto){
        return userService.editCustomer(customerDto);
    }

    // For ADMIN
    //////////////////////////////////////////////////////////////////////////////

    @GetMapping("employees")
    public List<User> getAllUsers(){
        return  userService.findAllUsers();
    }
    @GetMapping("employees/{user_id}")
    public User getUserById(@PathVariable String user_id){
        return  userService.findById(Long.parseLong(user_id));
    }

    @DeleteMapping("employees/{user_id}")
    public void deleteUser(@PathVariable String user_id){
        userService.deleteUser(Long.parseLong(user_id));
    }

}
