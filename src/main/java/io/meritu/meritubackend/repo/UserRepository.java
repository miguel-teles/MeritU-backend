package io.meritu.meritubackend.repo;

import io.meritu.meritubackend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);

    Optional<User> findByEmployee_Id(Long employeeId);
}
