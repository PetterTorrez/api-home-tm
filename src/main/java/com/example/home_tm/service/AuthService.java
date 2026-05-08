package com.example.home_tm.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.home_tm.dto.auth.AuthRequestDTO;
import com.example.home_tm.dto.auth.AuthResponseDTO;
import com.example.home_tm.security.JwtService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthResponseDTO autenticate(AuthRequestDTO authRequestDTO) {
        log.info("Entro a autenticate");
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequestDTO.getEmail(),
                authRequestDTO.getPassword()
            )
        );
        log.info("El token se va a generar con el correo: {}", authRequestDTO.getEmail());
        String token = jwtService.generateToken(authRequestDTO.getEmail());
        log.info("El token es: {}", token);

        return AuthResponseDTO.builder().token(token).build();
    }
}