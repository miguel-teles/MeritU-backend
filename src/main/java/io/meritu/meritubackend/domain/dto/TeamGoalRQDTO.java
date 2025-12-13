package io.meritu.meritubackend.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TeamGoalRQDTO extends GoalRQDTO {

    private Integer targetAmountGoalPoints;
    private Integer rewardTeamPoints;

    public TeamGoalRQDTO(Integer targetAmountGoalPoints,
                         Integer rewardTeamPoints,
                         String name,
                         String description,
                         Integer rewardCredits,
                         Long goalOwnerId,
                         Long teamGoalId,
                         LocalDateTime deadline) {
        this.targetAmountGoalPoints = targetAmountGoalPoints;
        this.rewardTeamPoints = rewardTeamPoints;
        this.name = name;
        this.description = description;
        this.rewardCredits = rewardCredits;
        this.goalOwnerId = goalOwnerId;
        this.teamGoalId = teamGoalId;
        this.deadline = deadline;
    }
}
