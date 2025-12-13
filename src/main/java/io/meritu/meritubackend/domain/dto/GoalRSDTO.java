package io.meritu.meritubackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.meritu.meritubackend.domain.entity.enums.GoalStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalRSDTO {
    private Long id;
    private String name;
    private String description;
    private Integer rewardCredits;
    private Integer amountGoalTeamPoints;
    private Integer rewardTeamPoints;
    private Long idGoalOwner;
    private boolean isPersonalGoal;
    private GoalStatus status;
    private LocalDateTime deadline;
    private List<GoalRSDTO> teamMembersGoals;
    private Long teamGoalId;
    private String employeeName;
    private String teamName;
    private Integer earnedPoints;
    private Integer completedGoals;
    private Integer amountOfPointsToAchieveGoal;
}
