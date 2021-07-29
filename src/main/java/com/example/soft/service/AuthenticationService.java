package com.example.soft.service;

import com.example.soft.dto.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity login(AuthenticationRequestDto requestDto);
}
