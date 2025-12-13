package io.meritu.meritubackend.domain.entity.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum GoalStatus {
    CANCELLED(1),
    COMPLETED(2),
    ACTIVE(3),
    EXPIRED(4);

    private int value;

    GoalStatus(int value) {
        this.value = value;
    }

    public static GoalStatus fromName(String goalName) {
        for (GoalStatus goal : GoalStatus.values()) {
            if (goal.name().equals(goalName)) {
                return goal;
            }
        }
        throw new IllegalArgumentException("GoalStatus '" + goalName + "' doesn't exist. Allowed values: " + Arrays.stream(GoalStatus.values()).map(r -> r.name()).collect(Collectors.joining(", ")));
    }

    public int getValue() {
        return value;
    }

    public static GoalStatus fromValue(int value) {
        for (GoalStatus goal : GoalStatus.values()) {
            if (goal.value == value) {
                return goal;
            }
        }
        throw new IllegalArgumentException("GoalStatus with value '" + value + "' doesn't exist. Allowed values: " + Arrays.stream(GoalStatus.values()).map(r -> r.name() + " (" + r.getValue() + ")").collect(Collectors.joining(", ")));
    }

    public boolean isCancelled() {
        return CANCELLED.value == this.value;
    }

    public boolean isActive() {
        return ACTIVE.value == this.value;
    }

    public boolean isCompleted() {
        return COMPLETED.value == this.value;
    }

    public boolean isExpired() {
        return EXPIRED.value == this.value;
    }
}
