package io.meritu.meritubackend.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamGoalRQDTO extends GoalRQDTO {

    private Integer amountGoalPoints;
    private Integer rewardTeamPoints;

    public TeamGoalRQDTO(Integer amountGoalPoints,
                         Integer rewardTeamPoints,
                         String name,
                         Integer rewardCredits,
                         Long goalOwnerId,
                         Long teamGoalId) {
        this.amountGoalPoints = amountGoalPoints;
        this.rewardTeamPoints = rewardTeamPoints;
        this.name = name;
        this.rewardCredits = rewardCredits;
        this.goalOwnerId = goalOwnerId;
        this.teamGoalId = teamGoalId;
    }
}
