package io.meritu.meritubackend.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IndividualGoalRQDTO extends GoalRQDTO {

    public IndividualGoalRQDTO(Integer rewardTeamPoints,
                               Long teamGoalId,
                               String name,
                               String description,
                               Integer rewardCredits,
                               Long goalOwnerId,
                               LocalDateTime deadline) {
        this.rewardTeamPoints = rewardTeamPoints;
        this.teamGoalId = teamGoalId;
        this.name = name;
        this.description = description;
        this.rewardCredits = rewardCredits;
        this.goalOwnerId = goalOwnerId;
        this.deadline = deadline;
    }
}
