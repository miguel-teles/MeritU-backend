package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IndividualGoalRQDTO extends GoalRQDTO {

    private Integer rewardTeamPoints;
    private List<IndividualGoalRQDTO> teamMembersGoals;
}
