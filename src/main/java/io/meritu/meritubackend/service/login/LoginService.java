package io.meritu.meritubackend.service.login;

import io.meritu.meritubackend.domain.dto.LoginRQDTO;
import io.meritu.meritubackend.domain.dto.LoginRSDTO;
import io.meritu.meritubackend.domain.dto.UserRSDTO;

public interface LoginService {

    LoginRSDTO logar(LoginRQDTO loginRQDTO);

    UserRSDTO getUsuarioLogado();
}
