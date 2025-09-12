package io.meritu.meritubackend.domain.dto;

import io.meritu.meritubackend.domain.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamDTO {
    private Long id;
    private String name;
    private Long managerId;
    private List<EmployeeRSDTO> employees;

    public static class Builder {
        private final TeamDTO teamDTO;

        public Builder() {
            teamDTO = new TeamDTO();
        }

        public Builder setId(Long id) {
            teamDTO.id = id;
            return this;
        }

        public Builder setName(String name) {
            teamDTO.name = name;
            return this;
        }

        public Builder setManagerId(Long managerId) {
            teamDTO.managerId = managerId;
            return this;
        }

        public Builder setEmployees(List<Employee> employees) {
            if (employees != null) {
                List<EmployeeRSDTO> employeeRSDTOS = new ArrayList<>();
                for (Employee employee : employees) {
                    employeeRSDTOS.add(employee.toDTO());
                }
                teamDTO.employees = employeeRSDTOS;
            }
            return this;
        }

        public TeamDTO build() {
            return teamDTO;
        }
    }
}
