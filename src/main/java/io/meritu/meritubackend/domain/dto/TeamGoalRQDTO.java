package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamGoalRQDTO extends GoalRQDTO {

    private Integer amountGoalPoints;
    private Integer rewardTeamPoints;
    private List<IndividualGoalRQDTO> teamMembersGoals;
}
