package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.GoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer points;
    private boolean isAchieved;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;
    private boolean isActive;

    public Goal(GoalRQDTO goalRQDTO) {
        this.name = goalRQDTO.getName();
        this.points = goalRQDTO.getPoints();
        this.isAchieved = false;
        this.isActive = true;
        if (Optional.ofNullable(goalRQDTO.getIsPersonalGoal()).orElse(Boolean.FALSE)) {
            this.employee = new Employee(goalRQDTO.getIdGoalOwner());
        } else {
            this.team = new Team(goalRQDTO.getIdGoalOwner());
        }
    }

    public GoalRSDTO toDTO() {
        return new GoalRSDTO(id,
                name,
                points,
                employee!=null ? employee.getId():team.getId(),
                team!=null,
                isAchieved,
                isActive);
    }
}
