package io.meritu.meritubackend.repo;

import io.meritu.meritubackend.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
