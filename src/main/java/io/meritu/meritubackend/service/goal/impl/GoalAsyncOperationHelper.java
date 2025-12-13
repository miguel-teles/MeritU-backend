package io.meritu.meritubackend.service.goal.impl;

import io.meritu.meritubackend.config.annotation.TeamGoalOperation;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.domain.entity.TeamGoal;
import io.meritu.meritubackend.domain.pojo.GoalType;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoalAsyncOperationHelper {

    private final GoalService teamGoalService;
    private final GoalOperationService goalOperationService;

    public GoalAsyncOperationHelper(@io.meritu.meritubackend.config.annotation.TeamGoal GoalService teamGoalService,
                                    @TeamGoalOperation GoalOperationService goalOperationService) {
        this.teamGoalService = teamGoalService;
        this.goalOperationService = goalOperationService;
    }

    @Async
    @Transactional
    public void completeTeamGoalIfPointsReached(Goal teamGoal) {
        TeamGoal goal = (TeamGoal) teamGoalService.findById(teamGoal.getId()).orElseThrow(() -> new GoalNotFoundException(teamGoal.getId(), GoalType.TEAM));

        if (wasTeamGoalPointsReached(goal)) {
            goalOperationService.completeGoal(goal.getId());
        }
    }

    private static boolean wasTeamGoalPointsReached(TeamGoal goal) {
        return goal
                .getTeamMemberGoals()
                .stream()
                .allMatch(tg->tg.getStatus().isCompleted());
    }
}
