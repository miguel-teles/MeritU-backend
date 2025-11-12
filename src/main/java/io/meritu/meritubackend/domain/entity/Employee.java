package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.EmployeeRQDTO;
import io.meritu.meritubackend.domain.dto.EmployeeRSDTO;
import jakarta.persistence.*;
import lombok.Getter;

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
    private Integer balance;
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;
    @OneToMany(cascade = CascadeType.ALL) //, mappedBy = "employee"
    private List<Goal> goals;
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
                    boolean isActive,
                    Role employeeRole) {
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
        this.employeeRole = employeeRole.getValue();
    }

    public EmployeeRSDTO toDTO() {
        return new EmployeeRSDTO(id, name, surname);
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void addBalance(Integer rewardCredits) {
        if (balance == null) {
            balance = rewardCredits;
        } else {
            this.balance += rewardCredits;
        }
    }
}
