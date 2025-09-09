package io.meritu.meritubackend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table
@Getter
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
}
