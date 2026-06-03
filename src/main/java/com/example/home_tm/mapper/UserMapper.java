package com.example.home_tm.mapper;

import com.example.home_tm.dto.user.UserRequestDTO;
import com.example.home_tm.dto.user.UserResponseDTO;
import com.example.home_tm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "name", source = "username")
    UserResponseDTO toResponseDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "name")
    @Mapping(target = "deletedAt", ignore = true)
    User toEntity(UserRequestDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "username", source = "name")
    void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User user);
}
