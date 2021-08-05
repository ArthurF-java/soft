package com.example.soft.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhoneNoUniqueException extends RuntimeException{

    public PhoneNoUniqueException(String message) {
        super(message);
    }
}
