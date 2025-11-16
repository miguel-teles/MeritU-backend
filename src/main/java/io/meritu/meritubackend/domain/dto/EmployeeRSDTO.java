package io.meritu.meritubackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeRSDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String role;
    private Integer balance;
    private Integer activeGoals;
    private Integer completedGoals;
}
