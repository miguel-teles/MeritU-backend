package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.domain.entity.*;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.exception.InvalidOperationGoalException;
import io.meritu.meritubackend.exception.TeamNotFoundException;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.team.TeamService;
import io.meritu.meritubackend.service.user.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamGoalOperationServiceImpl implements GoalOperationService {

    private final GoalService goalService;
    private final UserService userService;
    private final TeamService teamService;

    public TeamGoalOperationServiceImpl(@io.meritu.meritubackend.config.annotation.TeamGoal GoalService goalService,
                                        UserService userService,
                                        TeamService teamService) {
        this.goalService = goalService;
        this.userService = userService;
        this.teamService = teamService;
    }

    @Override
    @Transactional
    public Goal completeGoal(Long id) {
        TeamGoal goal = (TeamGoal) goalService.findById(id).orElseThrow(() -> new GoalNotFoundException(id));
        if (goal.isAchieved()) {
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

        List<User> teamMembers = new ArrayList<>();
        for (Employee employee : new ArrayList<>(team.getEmployees())) {
            teamMembers.add(giveCreditToTeamMember(goal, employee));
        }
        userService.saveAll(teamMembers);
    }

    @Transactional
    protected User giveCreditToTeamMember(TeamGoal goal, Employee employee) {
        User goalUser = userService.findByEmployeeId(employee.getId()).orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));
        goalUser.addBalance(goal.getRewardCredits());
        return goalUser;
    }
}
