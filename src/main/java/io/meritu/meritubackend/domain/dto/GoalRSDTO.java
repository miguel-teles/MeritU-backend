package io.meritu.meritubackend.domain.dto;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Team;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoalRSDTO {
    private Long id;
    private String name;
    private Integer points;
    private Long idGoalOwner;
    private boolean isPersonalGoal;
    private boolean isAchieved;
    private boolean isActive;
}
