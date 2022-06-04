package com.bnz.shared.users.roles;

public enum Roles {
    DEFAULT (0x1),
    STUDENT (0x2),
    TEACHER (0x4),
    ADMINISTRATOR (0x8);

    private final int value;

    Roles(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
