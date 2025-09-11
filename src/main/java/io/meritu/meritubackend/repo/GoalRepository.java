package io.meritu.meritubackend.repo;

import io.meritu.meritubackend.domain.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
