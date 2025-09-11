package io.meritu.meritubackend.service.resolver.goal;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.service.validator.goal.GoalValidator;

public interface GoalValidatorResolver {

    GoalValidator resolve(Goal goal);
}
