package com.example.soft.controllers;

import com.example.soft.dto.CustomerDto;
import com.example.soft.entity.User;
import com.example.soft.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    UserService userService;

    @GetMapping("/test")
    public List<User> test(){
        return userService.test();
    }


    @GetMapping
    public List<CustomerDto> findAllCustomers(){
        return userService.findAllByRoleCustomer();
    }

    @GetMapping("/{customer_id}")
    public CustomerDto findCustomerById(@PathVariable String customer_id) {
        return userService.findCustomerById(Long.parseLong(customer_id));

    }

    @PostMapping()
    public CustomerDto addCustomers(@RequestBody CustomerDto customerDto) {
        return userService.addCustomer(customerDto);
    }

    @PutMapping()
    public CustomerDto editCustomer(@RequestBody CustomerDto customerDto) {
        return userService.editCustomer(customerDto);
    }
}
