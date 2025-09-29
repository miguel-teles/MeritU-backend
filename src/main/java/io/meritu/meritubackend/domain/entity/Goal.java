package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.GoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.pojo.GoalType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    protected Integer rewardCredits;
    protected boolean isAchieved;
    protected boolean isActive;
    @LastModifiedDate
    protected Date dateChanged;
    @ManyToOne(fetch = FetchType.LAZY)
    protected Goal teamGoal;

    public Goal(Long id) {
        this.id = id;
    }

    public Goal(GoalRQDTO goalRQDTO) {
        this.id = goalRQDTO.getId();
        this.name = goalRQDTO.getName();
        this.rewardCredits = goalRQDTO.getRewardCredits();
        this.isAchieved = false;
        this.isActive = true;
    }


    public abstract GoalRSDTO toDTO();

    public abstract GoalType getGoalType();

    public void complete() {
        this.isAchieved = true;
    }
}
