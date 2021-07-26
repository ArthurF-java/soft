package com.example.soft.controllers;


import com.example.soft.dto.UserDto;
import com.example.soft.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping()
    public List<UserDto>getAllUsers(){
        return  userService.findAllUsers();
    }

    @GetMapping("/{user_id}")
    public UserDto getUserById(@PathVariable String user_id){
        return  userService.findById(Long.parseLong(user_id));
    }

    @DeleteMapping("/{user_id}")
    public String deleteUser(@PathVariable String user_id){
        return  userService.deleteUserById(Long.parseLong(user_id));

    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping()
    public UserDto editUser(@RequestBody UserDto userDto){
        return userService.editUser(userDto);
    }



}
