package io.meritu.meritubackend.service.validator.goal;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.pojo.GoalType;

public interface GoalValidator {

    void validatePersist(Goal goal);

    GoalType getGoalType();
}
