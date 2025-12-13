package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.Team;
import io.meritu.meritubackend.domain.entity.TeamGoal;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.exception.InvalidOperationGoalException;
import io.meritu.meritubackend.exception.TeamNotFoundException;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.team.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamGoalOperationServiceImpl implements GoalOperationService {

    private final GoalService goalService;
    private final EmployeeRepository employeeRepository;
    private final TeamService teamService;

    public TeamGoalOperationServiceImpl(@io.meritu.meritubackend.config.annotation.TeamGoal GoalService goalService,
                                        EmployeeRepository employeeRepository,
                                        TeamService teamService) {
        this.goalService = goalService;
        this.employeeRepository = employeeRepository;
        this.teamService = teamService;
    }

    @Override
    @Transactional
    public Goal completeGoal(Long id) {
        TeamGoal goal = (TeamGoal) goalService.findById(id).orElseThrow(() -> new GoalNotFoundException(id));
        if (goal.getStatus().isCompleted()) {
            throw new InvalidOperationGoalException("Goal already completed!");
        }
        goal.complete();
        giveCreditToTeamMembers(goal);
        return goalService.save(goal);
    }

    @Transactional
    protected void giveCreditToTeamMembers(TeamGoal goal) {
        Team team = teamService.findById(goal.getTeam().getId())
                .orElseThrow(() -> new TeamNotFoundException(goal.getTeam().getId()));

        List<Employee> teamMembers = new ArrayList<>();
        for (Employee employee : new ArrayList<>(team.getEmployees())) {
            teamMembers.add(giveCreditToTeamMember(goal, employee));
        }
        employeeRepository.saveAll(teamMembers);
    }

    @Transactional
    protected Employee giveCreditToTeamMember(TeamGoal goal, Employee employee) {
        Employee managedEmployee = employeeRepository.findById(employee.getId()).orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));
        managedEmployee.addBalance(goal.getRewardCredits());
        return employee;
    }
}
