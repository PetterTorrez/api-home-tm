package com.example.home_tm.mapper;

import com.example.home_tm.dto.user_group.CreateUserGroupRequestDto;
import com.example.home_tm.dto.user_group.CreateUserGroupResponseDto;
import com.example.home_tm.entity.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserGroupMapper {
    @Mapping(target = "id", ignore = true)
    UserGroup toEntity(CreateUserGroupRequestDto dto);

    @Mapping(target = "createdBy", source = "createdBy.id")
    CreateUserGroupResponseDto toDTO(UserGroup userGroup);
}
