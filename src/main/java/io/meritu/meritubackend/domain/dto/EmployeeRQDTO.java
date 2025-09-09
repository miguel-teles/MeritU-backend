package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeRQDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
}
