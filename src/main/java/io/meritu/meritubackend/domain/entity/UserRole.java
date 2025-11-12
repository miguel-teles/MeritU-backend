package io.meritu.meritubackend.domain.entity;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum UserRole {
    COMMON(1),
    ADMIN(2),
    GOD(3);

    private int value;

    UserRole(int value) {
        this.value = value;
    }

    public static UserRole fromName(String roleName) {
        for (UserRole role : UserRole.values()) {
            if (role.name().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("UserRole '" + roleName + "' doesn't exist. Allowed values: " + Arrays.stream(io.meritu.meritubackend.domain.entity.Role.values()).map(r -> r.name()).collect(Collectors.joining(", ")));
    }

    public int getValue() {
        return value;
    }

    public static UserRole fromValue(int value) {
        for (UserRole role : UserRole.values()) {
            if (role.value == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Role with value '" + value + "' doesn't exist. Allowed values: " + Arrays.stream(io.meritu.meritubackend.domain.entity.Role.values()).map(r -> r.name() + " (" + r.getValue() + ")").collect(Collectors.joining(", ")));
    }
}
