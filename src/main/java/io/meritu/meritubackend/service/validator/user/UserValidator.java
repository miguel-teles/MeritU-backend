package io.meritu.meritubackend.service.validator.user;

import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.service.validator.Validator;

public interface UserValidator extends Validator<User> {
    void validatePersist(User user);
}
