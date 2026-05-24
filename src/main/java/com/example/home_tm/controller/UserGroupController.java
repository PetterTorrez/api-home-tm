package com.example.home_tm.controller;

import com.example.home_tm.dto.user_group.CreateUserGroupRequestDto;
import com.example.home_tm.dto.user_group.CreateUserGroupResponseDto;
import com.example.home_tm.payload.ApiResponse;
import com.example.home_tm.security.AuthenticatedUser;
import com.example.home_tm.service.UserGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-groups")
public class UserGroupController {

    private final UserGroupService userGroupService;

    @PostMapping("/create-group")
    public ResponseEntity<
        ApiResponse<CreateUserGroupResponseDto>
    > createUserGroup(
        @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
        @Valid @RequestBody CreateUserGroupRequestDto body
    ) {
        CreateUserGroupResponseDto response =
            this.userGroupService.createUserGroup(
                authenticatedUser.getId(),
                body.getName()
            );

        return ResponseEntity.status(HttpStatus.OK).body(
            new ApiResponse<>(true, "User group created successfully", response)
        );
    }
}
