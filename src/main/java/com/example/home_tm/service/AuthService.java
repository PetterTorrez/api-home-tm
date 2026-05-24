package com.example.home_tm.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.home_tm.dto.auth.AuthRequestDTO;
import com.example.home_tm.dto.auth.AuthResponseDTO;
import com.example.home_tm.dto.user.UserRequestDTO;
import com.example.home_tm.dto.user.UserResponseDTO;
import com.example.home_tm.security.AuthenticatedUser;
import com.example.home_tm.security.CustomUserDetails;
import com.example.home_tm.security.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthResponseDTO autenticate(AuthRequestDTO authRequestDTO) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequestDTO.getEmail(),
                authRequestDTO.getPassword()
            )
        );

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        AuthenticatedUser authenticatedUser = AuthenticatedUser.builder()
                .id(userDetails.getId())
                .email(userDetails.getUsername())
                .build();

        String token = jwtService.generateToken(authenticatedUser);

        return AuthResponseDTO.builder().token(token).build();
    }

    public UserResponseDTO registerUser(UserRequestDTO userDto) {
        return this.userService.createUser(userDto);
    }
}