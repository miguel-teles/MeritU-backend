package io.meritu.meritubackend.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Long id) {
        super("Team '" + id + "' not found");
    }
}
