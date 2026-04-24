package com.example.home_tm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.home_tm.dto.RoleDTO;
import com.example.home_tm.payload.ApiResponse;
import com.example.home_tm.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getAllRoles() {
        List<RoleDTO> response = roleService.findAllRoles();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ApiResponse<>(true, "Roles retrieved successfully", response));
    }
}
