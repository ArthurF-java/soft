package com.example.soft.exeption_handing.users;

public class ProductDescriptionNotFound extends RuntimeException{
    public ProductDescriptionNotFound(String message) {
        super(message);
    }
}
