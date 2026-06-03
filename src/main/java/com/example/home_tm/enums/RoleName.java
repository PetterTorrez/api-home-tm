package com.example.home_tm.enums;

public enum RoleName {
    ADMIN(1),
    MANAGER(2),
    MEMBER(3);

    private final int value;

    RoleName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
