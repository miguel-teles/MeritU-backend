package io.meritu.meritubackend.service.validator.goal.impl;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.pojo.GoalType;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.service.validator.goal.GoalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalGoalValidatorImpl implements GoalValidator {

    private final EmployeeRepository employeeRepository;

    @Override
    public void validatePersist(Goal goal) {
        employeeRepository.findById(goal.getEmployee().getId())
                .orElseThrow(()->new EmployeeNotFoundException(goal.getEmployee().getId()));
    }

    @Override
    public GoalType getGoalType() {
        return GoalType.PERSONAL;
    }
}
