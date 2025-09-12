package io.meritu.meritubackend.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddEmployeeToTeamRQDTO {
    private List<Long> employeesIds;
    private Long teamId;
}
