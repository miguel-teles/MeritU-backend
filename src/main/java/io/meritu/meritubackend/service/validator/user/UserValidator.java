package io.meritu.meritubackend.service.validator.user;

import io.meritu.meritubackend.domain.entity.User;

public interface UserValidator {
    void validatePersist(User user);
}
