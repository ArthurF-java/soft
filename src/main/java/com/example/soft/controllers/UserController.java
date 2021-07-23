package com.example.soft.controllers;


import com.example.soft.entity.User;
import com.example.soft.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return  userService.findAllUsers();
    }

    @GetMapping("/users/{user_id}")
    public User getByPhone(@PathVariable String user_id){
        return  userService.findByPhone(user_id);

    }
}
