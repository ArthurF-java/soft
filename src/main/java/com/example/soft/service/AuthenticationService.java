package com.example.soft.service;

import com.example.soft.dto.AuthenticationRequestDto;
import com.example.soft.dto.AuthenticationResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    AuthenticationResponseDto login(AuthenticationRequestDto requestDto);
}
