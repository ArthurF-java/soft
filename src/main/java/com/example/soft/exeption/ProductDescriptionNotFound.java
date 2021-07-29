package com.example.soft.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductDescriptionNotFound extends RuntimeException{
    public ProductDescriptionNotFound(String message) {
        super(message);
    }
}
