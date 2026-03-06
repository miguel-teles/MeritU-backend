package io.meritu.meritubackend.controller.mapping;

import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.TeamGoal;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GoalToTeamGoalDTOMapper {

    List<GoalRSDTO> toTeamGoalListGetAll(List<Goal> goals);

    default GoalRSDTO toTeamGoalGetAll(Goal goal) {
        TeamGoal teamGoal = (TeamGoal) goal;

        Integer earnedPoints = 0;
        Integer completedGoals = 0;
        Integer amountOfPointsToAchieveGoal = 0;
        for (Goal teamMemberGoal : teamGoal.getTeamMemberGoals()) {
            if (teamMemberGoal.getStatus().isCompleted()) {
                earnedPoints += teamMemberGoal.getRewardTeamPoints();
                completedGoals++;
            }
            amountOfPointsToAchieveGoal += teamMemberGoal.getRewardTeamPoints();
        }

        return GoalRSDTO.builder()
                .id(teamGoal.getId())
                .name(teamGoal.getName())
                .description(teamGoal.getDescription())
                .deadline(teamGoal.getDeadline())
                .status(teamGoal.getStatus())
                .idGoalOwner(teamGoal.getTeam().getId())
                .teamName(teamGoal.getTeam().getName())
                .earnedPoints(earnedPoints)
                .isPersonalGoal(false)
                .rewardCredits(teamGoal.getRewardCredits())
                .rewardTeamPoints(teamGoal.getRewardTeamPoints())
                .amountOfPointsToAchieveGoal(amountOfPointsToAchieveGoal)
                .completedGoals(completedGoals)
                .build();
    }
}
