package io.meritu.meritubackend.repo;

import io.meritu.meritubackend.domain.entity.TeamGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamGoalRepository extends JpaRepository<TeamGoal, Long> {
}
