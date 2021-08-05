package com.example.soft.controllers;

import com.example.soft.dto.UserDto;
import com.example.soft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public UserDto getUserById(@PathVariable final long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable final long id) {
        userService.deleteUserById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public UserDto createUser(@RequestBody final UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public UserDto updateUser(@RequestBody final UserDto userDto) {
        return userService.updateUser(userDto);
    }
}
