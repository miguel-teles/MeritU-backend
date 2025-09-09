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

    public Employee(String name, String surname, boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
    }

    public EmployeeRSDTO toDTO() {
        return new EmployeeRSDTO(id, name, surname);
    }
}
