package io.meritu.meritubackend.service.login;

import io.meritu.meritubackend.domain.dto.LoginRQDTO;
import io.meritu.meritubackend.domain.dto.LoginRSDTO;

public interface LoginService {

    LoginRSDTO logar(LoginRQDTO loginRQDTO);

}
