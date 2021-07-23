package com.example.soft.service;

import com.example.soft.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findByPhone(String username);
}
