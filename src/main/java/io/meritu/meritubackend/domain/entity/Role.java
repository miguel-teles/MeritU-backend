package io.meritu.meritubackend.domain.entity;

public enum Role {
    EMPLOYEE(1),
    MANAGER(2);

    private int value;
    Role(int value) {}

    public int getValue() {
        return value;
    }

    public static Role fromValue(int value) {
        for (Role role : Role.values()) {
            if (role.value == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid value for Role: " + value);
    }
}
