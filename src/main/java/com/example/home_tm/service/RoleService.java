package com.example.home_tm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.home_tm.dto.RoleDTO;
import com.example.home_tm.mapper.RoleMapper;
import com.example.home_tm.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleDTO> findAllRoles() {
        return this.roleRepository.findAll().stream()
            .map(roleMapper::toDTO)
            .collect(Collectors.toList());
    }
}
