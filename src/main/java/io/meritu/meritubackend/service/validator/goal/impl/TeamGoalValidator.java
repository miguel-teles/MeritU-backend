package io.meritu.meritubackend.service.validator.goal.impl;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.TeamGoal;
import io.meritu.meritubackend.domain.pojo.GoalType;
import io.meritu.meritubackend.exception.TeamNotFoundException;
import io.meritu.meritubackend.repo.TeamRepository;
import io.meritu.meritubackend.service.validator.goal.GoalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamGoalValidator implements GoalValidator<TeamGoal> {

    private final TeamRepository teamRepository;

    @Override
    public void validatePersist(TeamGoal goal) {
        teamRepository.findById(goal.getTeam().getId())
                .orElseThrow(()->new TeamNotFoundException(goal.getTeam().getId()));
    }

    @Override
    public GoalType getGoalType() {
        return GoalType.TEAM;
    }
}
