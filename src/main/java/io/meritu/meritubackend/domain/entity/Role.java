package io.meritu.meritubackend.domain.entity;

import jakarta.validation.constraints.NotEmpty;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Role {
    EMPLOYEE(1),
    MANAGER(2);

    private int value;

    Role(int value) {
        this.value = value;
    }

    public static Role fromName(String roleName) {
        for (Role role : Role.values()) {
            if (role.name().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Role '" + roleName + "' doesn't exist. Allowed values: " + Arrays.stream(Role.values()).map(r -> r.name()).collect(Collectors.joining(", ")));
    }

    public int getValue() {
        return value;
    }

    public static Role fromValue(int value) {
        for (Role role : Role.values()) {
            if (role.value == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Role with value '" + value + "' doesn't exist. Allowed values: " + Arrays.stream(Role.values()).map(r -> r.name() + " (" + r.getValue() + ")").collect(Collectors.joining(", ")));
    }
}
