package com.example.soft.service.impl;

import com.example.soft.entity.User;
import com.example.soft.repository.UserRepository;
import com.example.soft.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByPhone(String username) {
        User user =  userRepository.findByPhone(username);
        return user;

    }


}
