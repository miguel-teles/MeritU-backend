package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalRQDTO {

    protected Long id;
    @NotEmpty
    protected String name;
    @NotNull
    protected Integer rewardCredits;
    @NotNull
    protected Long idGoalOwner;

}
