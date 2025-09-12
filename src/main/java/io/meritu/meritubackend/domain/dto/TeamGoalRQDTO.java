package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
                         Long idGoalOwner) {
        this.amountGoalPoints = amountGoalPoints;
        this.rewardTeamPoints = rewardTeamPoints;
        this.name = name;
        this.rewardCredits = rewardCredits;
        this.idGoalOwner = idGoalOwner;
    }
}
