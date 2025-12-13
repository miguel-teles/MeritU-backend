package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.EmployeeRQDTO;
import io.meritu.meritubackend.domain.dto.EmployeeRSDTO;
import io.meritu.meritubackend.domain.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.util.List;

@Getter
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer employeeRole;
    @Transient
    private Role employeeRoleTransiente;
    private Integer balance=0;
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<IndividualGoal> individualGoals;
    private boolean isActive;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(EmployeeRQDTO employeeRQDTO) {
        this.name = employeeRQDTO.getName();
        this.surname = employeeRQDTO.getSurname();
        this.isActive = true;
    }

    public Employee(String name,
                    String surname,
                    String email,
                    boolean isActive,
                    Role employeeRole) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isActive = isActive;
        this.employeeRole = employeeRole.getValue();
    }

    public EmployeeRSDTO toDTO() {
        if (employeeRoleTransiente == null) {
            employeeRoleTransiente = Role.fromValue(employeeRole);
        }

        Integer completedGoals = 0;
        Integer activeGoals = 0;
        if (Hibernate.isInitialized(individualGoals)) {
            for (IndividualGoal individualGoal : individualGoals) {
                if (individualGoal.status.isCompleted()) {
                    completedGoals++;
                } else if (individualGoal.status.isActive()) {
                    activeGoals++;
                }
            }
        }


        return new EmployeeRSDTO(id,
                name,
                surname,
                email,
                employeeRoleTransiente.getLabel(),
                balance,
                activeGoals,
                completedGoals);
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setIndividualGoals(List<IndividualGoal> goals) {
        this.individualGoals = goals;
    }

    public void addBalance(Integer rewardCredits) {
        if (balance == null) {
            balance = rewardCredits;
        } else {
            this.balance += rewardCredits;
        }
    }
}
