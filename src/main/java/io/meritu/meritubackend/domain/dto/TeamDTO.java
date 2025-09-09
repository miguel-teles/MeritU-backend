package io.meritu.meritubackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;
    private Long managerId;
}
