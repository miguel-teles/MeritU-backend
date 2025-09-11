package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.TeamDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToOne
    private Employee manager;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Goal> goals;
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
        return new TeamDTO(this.id,
                this.name,
                this.manager.getId());
    }
}
