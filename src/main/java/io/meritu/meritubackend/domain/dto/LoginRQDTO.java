package io.meritu.meritubackend.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRQDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
