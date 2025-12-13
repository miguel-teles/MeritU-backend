package io.meritu.meritubackend.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRQDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String role;
    @NotNull
    @Valid
    private EmployeeRQDTO employee;
}
