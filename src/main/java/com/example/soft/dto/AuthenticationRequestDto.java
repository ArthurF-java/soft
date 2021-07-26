package com.example.soft.dto;

import lombok.Data;


@Data
public class AuthenticationRequestDto {
    private String phone;
    private String password;
}
