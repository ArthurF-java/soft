package com.example.soft.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationResponseDto {
    private final String login;
    private final String token;
}
