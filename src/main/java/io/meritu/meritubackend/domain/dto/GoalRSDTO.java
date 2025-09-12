package io.meritu.meritubackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.meritu.meritubackend.domain.entity.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalRSDTO {
    private Long id;
    private String name;
    private Integer rewardCredits;
    private Integer amountGoalTeamPoints;
    private Integer rewardTeamPoints;
    private Long idGoalOwner;
    private boolean isPersonalGoal;
    private boolean isAchieved;
    private boolean isActive;
    private List<GoalRSDTO> teamMembersGoals;
    private Long teamGoalId;

    public static class Builder {

        private final GoalRSDTO dto;

        public Builder() {
            dto = new GoalRSDTO();
        }

        public GoalRSDTO build() {
            return dto;
        }

        public Builder setId(Long id) {
            dto.id = id;
            return this;
        }

        public Builder setName(String name) {
            dto.name = name;
            return this;
        }

        public Builder setRewardCredits(Integer rewardCredits) {
            dto.rewardCredits = rewardCredits;
            return this;
        }

        public Builder setAmountGoalTeamPoints(Integer amountGoalTeamPoints) {
            dto.amountGoalTeamPoints = amountGoalTeamPoints;
            return this;
        }

        public Builder setRewardTeamPoints(Integer rewardTeamPoints) {
            dto.rewardTeamPoints = rewardTeamPoints;
            return this;
        }

        public Builder setIdGoalOwner(Long idGoalOwner) {
            dto.idGoalOwner = idGoalOwner;
            return this;
        }

        public Builder setPersonalGoal(boolean personalGoal) {
            dto.isPersonalGoal = personalGoal;
            return this;
        }

        public Builder setAchieved(boolean achieved) {
            dto.isAchieved = achieved;
            return this;
        }

        public Builder setActive(boolean active) {
            dto.isActive = active;
            return this;
        }

        public Builder setTeamMembersGoals(List<Goal> goals) {
            if (dto.teamMembersGoals == null) {
                dto.teamMembersGoals = new ArrayList<>();
            }
            for (Goal goal : goals) {
                dto.teamMembersGoals.add(goal.toDTO());
            }
            return this;
        }

        public Builder setTeamGoalId(Long teamGoalId) {
            dto.teamGoalId = teamGoalId;
            return this;
        }
    }
}
