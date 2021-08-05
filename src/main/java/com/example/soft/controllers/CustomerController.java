package com.example.soft.controllers;

import com.example.soft.dto.CustomerDto;
import com.example.soft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public List<CustomerDto> findAllCustomers() {
        return userService.findAllByRoleCustomer();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public CustomerDto findCustomerById(@PathVariable final long id) {
        return userService.findCustomerById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public CustomerDto createCustomers(@RequestBody final CustomerDto customerDto) {
        return userService.createCustomer(customerDto);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public CustomerDto updateCustomer(@RequestBody final CustomerDto customerDto) {
        return userService.updateCustomer(customerDto);
    }
}
