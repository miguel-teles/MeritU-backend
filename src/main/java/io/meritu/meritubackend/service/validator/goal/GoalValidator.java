package io.meritu.meritubackend.service.validator.goal;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.pojo.GoalType;

public interface GoalValidator<T extends Goal> {

    void validatePersist(T goal);

    GoalType getGoalType();
}
