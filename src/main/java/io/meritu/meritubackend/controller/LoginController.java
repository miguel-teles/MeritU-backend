package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.LoginRQDTO;
import io.meritu.meritubackend.domain.dto.LoginRSDTO;
import io.meritu.meritubackend.domain.dto.UserRSDTO;
import io.meritu.meritubackend.service.login.LoginService;
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
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginRSDTO> logar(@RequestBody @Valid LoginRQDTO loginRqDTO) {
        return ResponseEntity.ok(loginService.logar(loginRqDTO));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<UserRSDTO> validarToken() {
        return ResponseEntity.ok(loginService.getUsuarioLogado());
    }

}
