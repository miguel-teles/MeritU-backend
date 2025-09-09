package io.meritu.meritubackend.service.user.impl;

import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.repo.UserRepository;
import io.meritu.meritubackend.service.user.UserService;
import io.meritu.meritubackend.service.validator.user.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmployeeId(Long id) {
        return userRepository.findByEmployee_Id(id);
    }

    @Override
    public User save(User user) {
        userValidator.validatePersist(user);
        user.encryptPassword(passwordEncoder);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
