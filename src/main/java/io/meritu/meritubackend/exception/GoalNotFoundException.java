package io.meritu.meritubackend.exception;

public class GoalNotFoundException extends RuntimeException {
    public GoalNotFoundException(Long id) {
        super("Goal '" + id + "' not found");
    }
}
