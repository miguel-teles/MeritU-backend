package io.meritu.meritubackend.domain.dto;

import io.meritu.meritubackend.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRSDTO {
    private Long id;
    private String username;
    private Role role;
    private Integer balance;
    private EmployeeRSDTO employee;
    private boolean isActive;
}
