package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.GoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Goal> teamMemberGoals;
    private Integer amountGoalPoints;
    private Integer rewardTeamPoints;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public TeamGoal(GoalRQDTO goalRQDTO) {
        super(goalRQDTO);
        this.amountGoalPoints = goalRQDTO.getAmountGoalPoints();
        this.rewardTeamPoints = goalRQDTO.getRewardTeamPoints();
        this.team = new Team(goalRQDTO.getIdGoalOwner());
        this.teamMemberGoals = new ArrayList<>();
        if (goalRQDTO.getTeamMembersGoals()!=null) {
            for (GoalRQDTO teamMembersGoal : goalRQDTO.getTeamMembersGoals()) {
                this.teamMemberGoals.add(new IndividualGoal(teamMembersGoal));
            }
        }
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
                .build();
    }

    @Override
    public GoalType getGoalType() {
        return GoalType.TEAM;
    }
}
