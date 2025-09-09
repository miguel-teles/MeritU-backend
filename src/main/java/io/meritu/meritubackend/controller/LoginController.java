package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.LoginRQDTO;
import io.meritu.meritubackend.domain.dto.LoginRSDTO;
import io.meritu.meritubackend.service.login.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginRSDTO> logar(@RequestBody @Valid LoginRQDTO loginRqDTO) {
        return ResponseEntity.ok(loginService.logar(loginRqDTO));
    }

}
