package com.example.home_tm.service;

import com.example.home_tm.dto.user_group.CreateUserGroupResponseDto;
import com.example.home_tm.entity.GroupMembership;
import com.example.home_tm.entity.Role;
import com.example.home_tm.entity.User;
import com.example.home_tm.entity.UserGroup;
import com.example.home_tm.enums.RoleName;
import com.example.home_tm.mapper.UserGroupMapper;
import com.example.home_tm.repository.GroupMembershipRepository;
import com.example.home_tm.repository.RoleRepository;
import com.example.home_tm.repository.UserGroupRepository;
import com.example.home_tm.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGroupService {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final GroupMembershipRepository groupMembershipRepository;
    private final RoleRepository roleRepository;
    private final UserGroupMapper userGroupMapper;

    @Transactional
    public CreateUserGroupResponseDto createUserGroup(
        Integer userId,
        String name
    ) {
        User user = this.userRepository.getReferenceById(userId);
        Role roleAdmin = this.roleRepository.findByName(
            RoleName.ADMIN
        ).orElseThrow(() ->
            new IllegalArgumentException("Role ADMIN not found")
        );

        Optional<UserGroup> groupFound =
            this.userGroupRepository.findByNameAndCreatedBy(name, user);

        groupFound.ifPresent(userGroup -> {
            throw new IllegalArgumentException(
                "Group Already exists with name by the same user"
            );
        });

        UserGroup userGroup = new UserGroup(name, user);
        UserGroup userGroupSaved = this.userGroupRepository.save(userGroup);

        GroupMembership groupMembership = new GroupMembership(
            userGroupSaved,
            user,
            roleAdmin
        );

        this.groupMembershipRepository.save(groupMembership);

        return userGroupMapper.toDTO(userGroupSaved);
    }
}
