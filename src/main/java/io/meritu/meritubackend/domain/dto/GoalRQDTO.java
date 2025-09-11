package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalRQDTO {
    @NotEmpty
    private String name;
    @NotNull
    private Integer points;
    @NotNull
    private Long idGoalOwner;
    @NotNull
    private Boolean isPersonalGoal;
}
