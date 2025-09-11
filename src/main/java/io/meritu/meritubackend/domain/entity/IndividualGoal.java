package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.GoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.pojo.GoalType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class IndividualGoal extends Goal {

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    private Integer rewardTeamPoints;

    public IndividualGoal(GoalRQDTO goalDTO) {
        super(goalDTO);
        this.employee = new Employee(goalDTO.getIdGoalOwner());
        this.rewardTeamPoints = goalDTO.getRewardTeamPoints();
    }

    @Override
    public GoalRSDTO toDTO() {
        return new GoalRSDTO.Builder()
                .setId(id)
                .setName(name)
                .setAchieved(isAchieved)
                .setActive(isActive)
                .setIdGoalOwner(employee.getId())
                .setPersonalGoal(true)
                .setRewardCredits(rewardCredits)
                .setRewardTeamPoints(rewardTeamPoints)
                .build();
    }

    @Override
    public GoalType getGoalType() {
        return GoalType.INDIVIDUAL;
    }
}
