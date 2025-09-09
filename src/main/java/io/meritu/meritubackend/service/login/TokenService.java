package io.meritu.meritubackend.service.login;

import io.meritu.meritubackend.domain.dto.LoginRSDTO;
import io.meritu.meritubackend.domain.entity.User;

public interface TokenService {
    LoginRSDTO gerarToken(User user);
    String validarToken(String token);
}
