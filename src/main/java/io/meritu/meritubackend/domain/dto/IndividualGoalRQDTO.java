package io.meritu.meritubackend.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualGoalRQDTO extends GoalRQDTO {

    private Integer rewardTeamPoints;
    private Long teamGoalId;
}
