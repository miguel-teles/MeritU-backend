package io.meritu.meritubackend.exception;

import io.meritu.meritubackend.domain.entity.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(User user) {
        super("User with id " + user.getId() + " not found");
    }

    public UserNotFoundException(Long userId) {
        super("User with id " + userId + " not found");
    }
}
