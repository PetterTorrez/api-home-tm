package com.example.home_tm.service;

import com.example.home_tm.dto.user_group.CreateUserGroupResponseDto;
import com.example.home_tm.entity.User;
import com.example.home_tm.entity.UserGroup;
import com.example.home_tm.mapper.UserGroupMapper;
import com.example.home_tm.repository.UserGroupRepository;
import com.example.home_tm.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGroupService {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final UserGroupMapper userGroupMapper;

    @Transactional
    public CreateUserGroupResponseDto createUserGroup(
        Integer userId,
        String name
    ) {
        User user = this.userRepository.getReferenceById(userId);

        UserGroup userGroup = new UserGroup();
        userGroup.setName(name);
        userGroup.setCreatedBy(user);

        this.userGroupRepository.save(userGroup);
        return userGroupMapper.toDTO(userGroup);
    }
}
