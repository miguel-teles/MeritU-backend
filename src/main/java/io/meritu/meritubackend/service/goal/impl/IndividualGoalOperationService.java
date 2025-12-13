package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.exception.InvalidOperationGoalException;
import io.meritu.meritubackend.service.employee.EmployeeService;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndividualGoalOperationService implements GoalOperationService {

    private final GoalService goalService;
    private final EmployeeService employeeService;
    private final GoalAsyncOperationHelper goalAsyncOperationHelper;

    public IndividualGoalOperationService(@io.meritu.meritubackend.config.annotation.IndividualGoal GoalService goalService,
                                          EmployeeService employeeService,
                                          GoalAsyncOperationHelper goalAsyncOperationHelper) {
        this.goalService = goalService;
        this.employeeService = employeeService;
        this.goalAsyncOperationHelper = goalAsyncOperationHelper;
    }

    @Override
    @Transactional
    public Goal completeGoal(Long id) {
        IndividualGoal goal = (IndividualGoal) goalService.findById(id).orElseThrow(() -> new GoalNotFoundException(id));
        if (goal.getStatus().isCompleted()) {
            throw new InvalidOperationGoalException("Goal already completed!");
        }
        goal.complete();
        giveCreditToEmployee(goal);
        goal = (IndividualGoal) goalService.save(goal);
        if (goal.getTeamGoal() != null) {
            goalAsyncOperationHelper.completeTeamGoalIfPointsReached(goal.getTeamGoal());
        }
        return goal;
    }

    @Transactional
    protected void giveCreditToEmployee(IndividualGoal goal) {
        Employee goalEmployee = employeeService.findById(goal.getEmployee().getId()).orElseThrow(() -> new EmployeeNotFoundException(goal.getEmployee().getId()));
        goalEmployee.addBalance(goal.getRewardCredits());
        employeeService.save(goalEmployee);
    }
}
