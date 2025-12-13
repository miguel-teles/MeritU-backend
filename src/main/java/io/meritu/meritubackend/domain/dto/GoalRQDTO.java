package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GoalRQDTO {

    protected Long id;
    @NotEmpty
    protected String name;
    @NotEmpty
    protected String description;
    @NotNull
    protected Integer rewardCredits;
    @NotNull
    protected Long goalOwnerId;
    protected Long goalId;
    @NotNull
    protected LocalDateTime deadline;
    protected Integer rewardTeamPoints;
    protected Long teamGoalId;

}
