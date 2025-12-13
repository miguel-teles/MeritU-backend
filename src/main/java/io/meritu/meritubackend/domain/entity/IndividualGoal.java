package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.entity.enums.GoalStatus;
import io.meritu.meritubackend.domain.pojo.GoalType;
import jakarta.persistence.*;
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

    public IndividualGoal(Long id) {
        super(id);
    }

    public IndividualGoal(IndividualGoalRQDTO goalDTO) {
        super(goalDTO);
        this.employee = new Employee(goalDTO.getGoalOwnerId());
        if (goalDTO.getTeamGoalId() != null) {
            this.teamGoal = new TeamGoal(goalDTO.getTeamGoalId());
        }
        this.description = getDescription();
        this.deadline = goalDTO.getDeadline();
        this.status = GoalStatus.ACTIVE;
    }

    @Override
    public GoalRSDTO toDTO() {
        return GoalRSDTO.builder()
                .id(id)
                .name(name)
                .description(description)
                .deadline(deadline)
                .status(status)
                .idGoalOwner(employee.getId())
                .employeeName(employee.getName())
                .isPersonalGoal(true)
                .rewardCredits(rewardCredits)
                .rewardTeamPoints(rewardTeamPoints)
                .teamGoalId(teamGoal.id)
                .build();
    }

    @Override
    public GoalType getGoalType() {
        return GoalType.INDIVIDUAL;
    }
}
