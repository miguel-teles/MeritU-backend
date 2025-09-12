package io.meritu.meritubackend.service.goal;

import io.meritu.meritubackend.domain.entity.Goal;

public interface GoalOperationService {

    Goal completeGoal(Long id);

}
