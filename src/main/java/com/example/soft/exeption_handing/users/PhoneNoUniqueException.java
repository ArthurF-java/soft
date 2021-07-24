package com.example.soft.exeption_handing.users;

public class PhoneNoUniqueException extends RuntimeException{

    public PhoneNoUniqueException(String message) {
        super(message);
    }
}
