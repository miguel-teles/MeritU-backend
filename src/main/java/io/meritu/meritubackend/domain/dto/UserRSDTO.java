package io.meritu.meritubackend.domain.dto;

import io.meritu.meritubackend.domain.entity.Role;
import io.meritu.meritubackend.domain.entity.UserRole;
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
    private UserRole role;
    private EmployeeRSDTO employee;
    private boolean isActive;
}
