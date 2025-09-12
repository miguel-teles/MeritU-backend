package io.meritu.meritubackend.service.goal;

import io.meritu.meritubackend.domain.entity.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalService {

    Goal save(Goal goal);

    List<Goal> findAll();

    Optional<Goal> findById(Long id);

}
