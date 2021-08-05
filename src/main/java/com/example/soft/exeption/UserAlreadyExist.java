package com.example.soft.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CREATED)
public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist(String message) {
        super(message);
    }
}
