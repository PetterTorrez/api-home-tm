package com.example.home_tm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.home_tm.dto.user.UserResponseDTO;
import com.example.home_tm.dto.user.UserUpdateRequestDTO;
import com.example.home_tm.payload.ApiResponse;
import com.example.home_tm.security.AuthenticatedUser;
import com.example.home_tm.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @PathVariable Integer id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ApiResponse<>(true, "User found successfully", this.userService.findUserById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@PathVariable Integer id, @Valid @RequestBody UserUpdateRequestDTO userDto) {
        
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ApiResponse<>(true, "User updated successfully", this.userService.updateUser(id, userDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> deleteUserById(@PathVariable Integer id) {
        this.userService.deleteUserById(id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ApiResponse<>(true, "User deleted successfully", null));
    }
}
