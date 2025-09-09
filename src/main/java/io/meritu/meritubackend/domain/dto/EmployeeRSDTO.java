package io.meritu.meritubackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeRSDTO {
    private Long id;
    private String name;
    private String surname;
}
