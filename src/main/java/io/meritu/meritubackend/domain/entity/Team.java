package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.TeamDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, mappedBy = "team")
    private List<Employee> employees;
    @OneToOne
    @JoinColumn(unique = true)
    private Employee manager;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<TeamGoal> teamGoals;
    private boolean isActive;

    public Team(Long id) {
        this.id = id;
    }

    public Team(String name, Employee manager) {
        this.name = name;
        this.isActive = true;
        this.manager = manager;
    }

    public TeamDTO toDTO() {
        return new TeamDTO.Builder()
                .setId(this.id)
                .setName(this.name)
                .setEmployees(this.getEmployees())
                .setManagerId(this.manager.getId())
                .build();
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
