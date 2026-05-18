package com.example.home_tm.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class AuthenticatedUser {
    private Integer id;
    private String email;
    private String name;
}
