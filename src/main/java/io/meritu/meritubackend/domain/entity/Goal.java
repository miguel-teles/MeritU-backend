package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.GoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.entity.enums.GoalStatus;
import io.meritu.meritubackend.domain.pojo.GoalType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected String description;
    protected Integer rewardCredits;
    private Integer statusValue;
    @Transient
    protected GoalStatus status;
    @LastModifiedDate
    protected Date updateDate;
    @ManyToOne(fetch = FetchType.LAZY)
    protected Goal teamGoal;
    protected LocalDateTime deadline;
    protected Integer rewardTeamPoints;

    public Goal(Long id) {
        this.id = id;
    }

    public Goal(GoalRQDTO goalRQDTO) {
        this.id = goalRQDTO.getId();
        this.name = goalRQDTO.getName();
        this.rewardCredits = goalRQDTO.getRewardCredits();
        this.description = goalRQDTO.getDescription();
        this.status = GoalStatus.ACTIVE;
        this.statusValue = status.getValue();
        this.deadline = goalRQDTO.getDeadline();
        this.rewardTeamPoints = goalRQDTO.getRewardTeamPoints();
    }

    @PostLoad
    void postLoad() {
        if (statusValue != null) {
            this.status = GoalStatus.fromValue(statusValue);
        }
    }

    @PrePersist
    void prePersist() {
        if (status != null) {
            this.statusValue = status.getValue();
        }
    }

    public abstract GoalRSDTO toDTO();

    public abstract GoalType getGoalType();

    public void complete() {
        this.status = GoalStatus.COMPLETED;
        this.statusValue = GoalStatus.COMPLETED.getValue();
    }

    public void setTeamGoal(Goal teamGoal) {
        this.teamGoal = teamGoal;
    }
}
