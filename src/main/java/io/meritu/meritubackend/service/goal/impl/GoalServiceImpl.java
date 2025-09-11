package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.repo.GoalRepository;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.resolver.goal.GoalValidatorResolver;
import io.meritu.meritubackend.service.validator.goal.GoalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final GoalValidatorResolver goalValidatorResolver;

    @Override
    public Goal save(Goal goal) {
        goalValidatorResolver.resolve(goal).validatePersist(goal);
        return goalRepository.save(goal);
    }

    @Override
    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    @Override
    public Optional<Goal> findById(Long id) {
        return goalRepository.findById(id);
    }
}
