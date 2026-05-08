package com.example.home_tm.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.home_tm.dto.auth.AuthRequestDTO;
import com.example.home_tm.dto.auth.AuthResponseDTO;
import com.example.home_tm.security.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthResponseDTO autenticate(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequestDTO.getEmail(),
                authRequestDTO.getPassword()
            )
        );

        String token = jwtService.generateToken(authRequestDTO.getEmail());

        return AuthResponseDTO.builder().token(token).build();
    }
}