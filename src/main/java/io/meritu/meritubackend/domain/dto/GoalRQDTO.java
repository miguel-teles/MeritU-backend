package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoalRQDTO {
    @NotEmpty
    private String name;

    private Integer rewardCredits;
    private Integer rewardTeamPoints;
    private Integer amountGoalPoints;

    @NotNull
    private Long idGoalOwner;
    @NotNull
    private Boolean isPersonalGoal;
    private List<GoalRQDTO> teamMembersGoals;
}
