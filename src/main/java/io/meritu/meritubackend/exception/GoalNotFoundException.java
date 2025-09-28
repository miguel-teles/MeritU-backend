package io.meritu.meritubackend.exception;

import io.meritu.meritubackend.domain.pojo.GoalType;

public class GoalNotFoundException extends RuntimeException {
    public GoalNotFoundException(Long id) {
        super("Goal '" + id + "' não existe");
    }

    public GoalNotFoundException(Long id, GoalType goalType) {
        super(goalType.getValue() + " goal '" + id + "' não existe");
    }
}
