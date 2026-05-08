package com.example.home_tm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.home_tm.dto.auth.AuthRequestDTO;
import com.example.home_tm.dto.auth.AuthResponseDTO;
import com.example.home_tm.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO requestDto) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(authService.autenticate(requestDto));
    }
    
}
