package io.meritu.meritubackend.service.validator.user.impl;

import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.exception.InvalidUserException;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.repo.UserRepository;
import io.meritu.meritubackend.service.validator.user.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidatorImpl implements UserValidator {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void validatePersist(User user) {
        if (user.getEmployee() == null) {
            throw new InvalidUserException("Employee can't be null");
        } else if (user.getEmployee().getId() != null && user.getId() == null) {
            throw new InvalidUserException("Can't persist User with already created Employee");
        } else {
            User userByUsername = userRepository.findUserByUsername(user.getUsername());
            if (userByUsername != null && (userByUsername.getId() == null || !userByUsername.getId().equals(user.getId()))) {
                throw new InvalidUserException("Username already exists");
            }
        }
    }
}
