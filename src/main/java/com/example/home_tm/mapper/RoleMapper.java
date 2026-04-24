package com.example.home_tm.mapper;

import org.mapstruct.Mapper;

import com.example.home_tm.dto.RoleDTO;
import com.example.home_tm.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
}