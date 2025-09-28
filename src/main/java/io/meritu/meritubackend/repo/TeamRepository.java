package io.meritu.meritubackend.repo;

import io.meritu.meritubackend.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByManagerId(Long managerId);
}
