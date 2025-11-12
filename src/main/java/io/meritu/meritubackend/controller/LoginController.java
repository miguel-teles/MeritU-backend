package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.LoginRQDTO;
import io.meritu.meritubackend.domain.dto.LoginRSDTO;
import io.meritu.meritubackend.domain.dto.UserRSDTO;
import io.meritu.meritubackend.service.login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and get a token")
    public ResponseEntity<LoginRSDTO> logar(@RequestBody @Valid LoginRQDTO loginRqDTO) {
        return ResponseEntity.ok(loginService.logar(loginRqDTO));
    }

    @PostMapping("/validateToken")
    @Operation(summary = "Validate token and get user information")
    public ResponseEntity<UserRSDTO> validarToken() {
        return ResponseEntity.ok(loginService.getUsuarioLogado());
    }

}
