package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.TeamGoal;
import io.meritu.meritubackend.repo.GoalRepository;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.resolver.goal.GoalValidatorResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamGoalService implements GoalService<TeamGoal> {

    private final GoalRepository teamGoalRepository;
    private final GoalValidatorResolver goalValidatorResolver;

    @Override
    public TeamGoal save(TeamGoal goal) {
        goalValidatorResolver.resolve(goal).validatePersist(goal);
        return teamGoalRepository.save(goal);
    }

    @Override
    public List<TeamGoal> findAll() {
        return (List<TeamGoal>)(List<?>) teamGoalRepository.findAll();
    }

    @Override
    public Optional<TeamGoal> findById(Long id) {
        return (Optional<TeamGoal>)(Optional<?>) teamGoalRepository.findById(id);
    }
}
