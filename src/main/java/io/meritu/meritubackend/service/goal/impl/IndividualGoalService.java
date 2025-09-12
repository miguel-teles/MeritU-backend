package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.repo.GoalRepository;
import io.meritu.meritubackend.repo.IndividualGoalRepository;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.resolver.goal.GoalValidatorResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualGoalService implements GoalService {

    private final IndividualGoalRepository goalRepository;
    private final GoalValidatorResolver goalValidatorResolver;

    @Override
    public Goal save(Goal goal) {
        goalValidatorResolver.resolve(goal).validatePersist(goal);
        return goalRepository.save((IndividualGoal) goal);
    }

    @Override
    public List<Goal> findAll() {
        return (List<Goal>) (List<?>) goalRepository.findAll();
    }

    @Override
    public Optional<Goal> findById(Long id) {
        return goalRepository.findById(id).map(goal -> (Goal) goal);
    }
}
