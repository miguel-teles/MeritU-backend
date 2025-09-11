package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.repo.GoalRepository;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.resolver.goal.GoalValidatorResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualGoalService implements GoalService<IndividualGoal> {

    private final GoalRepository goalRepository;
    private final GoalValidatorResolver goalValidatorResolver;

    @Override
    public IndividualGoal save(IndividualGoal goal) {
        goalValidatorResolver.resolve(goal).validatePersist(goal);
        return goalRepository.save(goal);
    }

    @Override
    public List<IndividualGoal> findAll() {
        return (List<IndividualGoal>)(List<?>) goalRepository.findAll();
    }

    @Override
    public Optional<IndividualGoal> findById(Long id) {
        return (Optional<IndividualGoal>)(Optional<?>) goalRepository.findById(id);
    }
}
