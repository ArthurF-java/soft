package com.example.soft.controllers;

import com.example.soft.dto.AuthenticationRequestDto;
import com.example.soft.dto.AuthenticationResponseDto;
import com.example.soft.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody final AuthenticationRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }
}