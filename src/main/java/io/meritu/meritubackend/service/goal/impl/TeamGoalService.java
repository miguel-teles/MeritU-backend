package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.Team;
import io.meritu.meritubackend.domain.entity.TeamGoal;
import io.meritu.meritubackend.repo.GoalRepository;
import io.meritu.meritubackend.repo.TeamGoalRepository;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.resolver.goal.GoalValidatorResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamGoalService implements GoalService {

    private final TeamGoalRepository teamGoalRepository;
    private final GoalValidatorResolver goalValidatorResolver;

    @Override
    public Goal save(Goal goal) {
        goalValidatorResolver.resolve(goal).validatePersist(goal);
        return teamGoalRepository.save((TeamGoal) goal);
    }

    @Override
    public List<Goal> findAll() {
        return (List<Goal>) (List<?>) teamGoalRepository.findAll();
    }

    @Override
    public Optional<Goal> findById(Long id) {
        return teamGoalRepository.findById(id).map(goal -> (Goal) goal);
    }
}
