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

import java.util.Optional;

@Service
public class IndividualGoalOperationService implements GoalOperationService {

    private final GoalService goalService;
    private final UserService userService;

    public IndividualGoalOperationService(@io.meritu.meritubackend.config.annotation.IndividualGoal GoalService goalService,
                                          UserService userService) {
        this.goalService = goalService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Goal completeGoal(Long id) {
        IndividualGoal goal = (IndividualGoal) goalService.findById(id).orElseThrow(() -> new GoalNotFoundException(id));
        if (goal.isAchieved()) {
            throw new InvalidOperationGoalException("Goal already completed!");
        }
        goal.complete();
        giveCreditToEmployee(goal);
        goal = (IndividualGoal) goalService.save(goal);
        return goal;
    }

    @Transactional
    protected void giveCreditToEmployee(IndividualGoal goal) {
        Employee employee = goal.getEmployee();
        User goalUser = userService.findByEmployeeId(employee.getId()).orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));
        goalUser.addBalance(goal.getRewardCredits());
        userService.save(goalUser);
    }
}
