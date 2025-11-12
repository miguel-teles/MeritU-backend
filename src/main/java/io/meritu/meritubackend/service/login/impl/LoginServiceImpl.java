package io.meritu.meritubackend.service.login.impl;

import io.meritu.meritubackend.domain.dto.LoginRQDTO;
import io.meritu.meritubackend.domain.dto.LoginRSDTO;
import io.meritu.meritubackend.domain.dto.UserRSDTO;
import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.exception.UserNotFoundException;
import io.meritu.meritubackend.repo.UserRepository;
import io.meritu.meritubackend.service.login.LoginService;
import io.meritu.meritubackend.service.login.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public LoginRSDTO logar(LoginRQDTO loginRQDTO) {
        validaExistenciaUsuario(loginRQDTO);
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRQDTO.getUsername(), loginRQDTO.getPassword());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePassword);
        return tokenService.gerarToken((User) authenticate.getPrincipal());
    }

    @Override
    public UserRSDTO getUsuarioLogado() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).toDTO();
    }

    private void validaExistenciaUsuario(LoginRQDTO loginRQDTO) {
        if (userRepository.findByUsername(loginRQDTO.getUsername()) == null) {
            throw new UserNotFoundException("Usuário com login '" + loginRQDTO.getUsername() + "' não existe");
        }
    }
}

