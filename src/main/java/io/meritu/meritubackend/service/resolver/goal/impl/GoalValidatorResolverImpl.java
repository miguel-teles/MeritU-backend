package io.meritu.meritubackend.service.resolver.goal.impl;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.pojo.GoalType;
import io.meritu.meritubackend.service.resolver.goal.GoalValidatorResolver;
import io.meritu.meritubackend.service.validator.goal.GoalValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GoalValidatorResolverImpl implements GoalValidatorResolver {

    private final Map<GoalType, GoalValidator> validators = new HashMap<>();

    public GoalValidatorResolverImpl(List<GoalValidator> validators) {
        for (GoalValidator validator : validators) {
            this.validators.put(validator.getGoalType(), validator);
        }
    }

    @Override
    public GoalValidator resolve(Goal goal) {
        return this.validators.get(goal.getGoalType());
    }
}
