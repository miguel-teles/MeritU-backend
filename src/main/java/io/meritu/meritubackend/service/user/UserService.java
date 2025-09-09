package io.meritu.meritubackend.service.user;

import io.meritu.meritubackend.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
    Optional<User> findByEmployeeId(Long id);
    User save(User user);
    List<User> findAll();

}
