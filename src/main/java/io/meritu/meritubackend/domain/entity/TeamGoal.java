package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.GoalRQDTO;
import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.dto.TeamGoalRQDTO;
import io.meritu.meritubackend.domain.pojo.GoalType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Integer amountGoalPoints;
    private Integer rewardTeamPoints;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Team team;

    public TeamGoal(Long id) {
        this.id = id;
    }

    public TeamGoal(TeamGoalRQDTO teamGoalRQDTO) {
        super(teamGoalRQDTO);
        this.amountGoalPoints = teamGoalRQDTO.getAmountGoalPoints();
        this.rewardTeamPoints = teamGoalRQDTO.getRewardTeamPoints();
        this.team = new Team(teamGoalRQDTO.getIdGoalOwner());
        this.teamMemberGoals = new ArrayList<>();
    }

    @Override
    public GoalRSDTO toDTO() {
        return new GoalRSDTO.Builder()
                .setId(id)
                .setName(name)
                .setAchieved(isAchieved)
                .setActive(isActive)
                .setIdGoalOwner(team.getId())
                .setPersonalGoal(true)
                .setRewardCredits(rewardCredits)
                .setRewardTeamPoints(rewardTeamPoints)
                .setAmountGoalTeamPoints(amountGoalPoints)
                .setTeamMembersGoals(this.teamMemberGoals)
                .build();
    }

    @Override
    public GoalType getGoalType() {
        return GoalType.TEAM;
    }
}
