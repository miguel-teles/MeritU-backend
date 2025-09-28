package io.meritu.meritubackend.service.validator.goal.impl;

import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.domain.pojo.GoalType;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.repo.GoalRepository;
import io.meritu.meritubackend.repo.TeamGoalRepository;
import io.meritu.meritubackend.service.validator.goal.GoalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndividualGoalValidatorImpl implements GoalValidator<IndividualGoal> {

    private final EmployeeRepository employeeRepository;
    private final TeamGoalRepository teamGoalRepository;

    @Override
    public void validatePersist(IndividualGoal goal) {
        employeeRepository.findById(goal.getEmployee().getId())
                .orElseThrow(() -> new EmployeeNotFoundException(goal.getEmployee().getId()));

        if (goal.getTeamGoal() != null) {
            teamGoalRepository.findById(goal.getTeamGoal().getId())
                    .orElseThrow(() -> new GoalNotFoundException(goal.getTeamGoal().getId(), GoalType.TEAM));
        }
    }

    @Override
    public GoalType getGoalType() {
        return GoalType.INDIVIDUAL;
    }
}
