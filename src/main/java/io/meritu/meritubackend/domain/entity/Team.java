package io.meritu.meritubackend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table
@Getter
public class Team {
    @Id
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToOne
    private Employee manager;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Goal> goals;
    private boolean isActive;

}
