package io.meritu.meritubackend.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualGoalRQDTO extends GoalRQDTO {

    private Integer rewardTeamPoints;
    private Long teamGoalId;

    public IndividualGoalRQDTO(Integer rewardTeamPoints,
                               Long teamGoalId,
                               String name,
                               Integer rewardCredits,
                               Long idGoalOwner) {
        this.rewardTeamPoints = rewardTeamPoints;
        this.teamGoalId = teamGoalId;
        this.name = name;
        this.rewardCredits = rewardCredits;
        this.idGoalOwner = idGoalOwner;
    }
}
