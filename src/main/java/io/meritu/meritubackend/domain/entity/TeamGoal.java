package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.dto.TeamGoalRQDTO;
import io.meritu.meritubackend.domain.entity.enums.GoalStatus;
import io.meritu.meritubackend.domain.pojo.GoalType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TeamGoal extends Goal {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teamGoal")
    private List<Goal> teamMemberGoals;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Team team;

    public TeamGoal(Long id) {
        this.id = id;
    }

    public TeamGoal(TeamGoalRQDTO teamGoalRQDTO) {
        super(teamGoalRQDTO);
        this.team = new Team(teamGoalRQDTO.getGoalOwnerId());
        this.teamMemberGoals = new ArrayList<>();
        this.description = teamGoalRQDTO.getDescription();
        this.deadline = teamGoalRQDTO.getDeadline();
        this.status = GoalStatus.ACTIVE;
    }

    @Override
    public GoalRSDTO toDTO() {
        Integer earnedPoints = 0;
        Integer completedGoals = 0;
        Integer amountOfPointsToAchieveGoal = 0;
        List<GoalRSDTO> individualGoalList = new ArrayList<>();
        for (Goal teamMemberGoal : this.teamMemberGoals) {
            if (teamMemberGoal.status.isCompleted()) {
                earnedPoints += teamMemberGoal.rewardTeamPoints;
                completedGoals++;
            }
            individualGoalList.add(teamMemberGoal.toDTO());
            amountOfPointsToAchieveGoal += teamMemberGoal.rewardTeamPoints;
        }

        return GoalRSDTO.builder()
                .id(id)
                .name(name)
                .description(description)
                .deadline(deadline)
                .status(status)
                .idGoalOwner(team.getId())
                .teamName(team.getName())
                .earnedPoints(earnedPoints)
                .isPersonalGoal(false)
                .rewardCredits(rewardCredits)
                .rewardTeamPoints(rewardTeamPoints)
                .teamMembersGoals(individualGoalList)
                .amountOfPointsToAchieveGoal(amountOfPointsToAchieveGoal)
                .completedGoals(completedGoals)
                .build();
    }

    @Override
    public GoalType getGoalType() {
        return GoalType.TEAM;
    }
}
