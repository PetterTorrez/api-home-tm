package com.example.home_tm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.home_tm.dto.user.UserRequestDTO;
import com.example.home_tm.dto.user.UserResponseDTO;
import com.example.home_tm.entity.User;
import com.example.home_tm.payload.ApiResponse;
import com.example.home_tm.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return this.userService.findUserById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO userDto) {
        UserResponseDTO userResponse = this.userService.createUser(userDto);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true, "User created successfully", userResponse));
    }
}
