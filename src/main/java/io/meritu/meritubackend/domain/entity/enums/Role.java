package io.meritu.meritubackend.domain.entity.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Role {
    EMPLOYEE(1, "Colaborador"), //TODO: depois mudar para aceitar em ingles as coisas
    MANAGER(2, "Gerente");

    private int value;
    private String label;

    Role(int value, String label) {
        this.value = value;
        this.label = label;
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

    public String getLabel() {
        return label;
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
