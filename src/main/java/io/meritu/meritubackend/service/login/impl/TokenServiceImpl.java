package io.meritu.meritubackend.service.login.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.meritu.meritubackend.domain.dto.LoginRSDTO;
import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.exception.AuthException;
import io.meritu.meritubackend.service.login.TokenService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenServiceImpl implements TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private Algorithm algorithm;

    @Override
    public LoginRSDTO gerarToken(User user) {
        try {
            Integer EXPIRES_IN_SECONDS = 3600;
            return new LoginRSDTO(JWT.create()
                    .withIssuer("xquote-back")
                    .withSubject(user.getUsername())
                    .withExpiresAt(definirExpiracaoToken(EXPIRES_IN_SECONDS))
                    .sign(algorithm),
                    EXPIRES_IN_SECONDS,
                    user.toDTO());
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao criar token", ex); //dps melhorar essa exception
        }
    }

    public Instant definirExpiracaoToken(Integer expiracaoIn) {
        return LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")).plusSeconds(expiracaoIn);
    }

    @Override
    public String validarToken(String token) {
        return JWT.require(algorithm)
                .withIssuer("xquote-back")
                .build()
                .verify(token)
                .getSubject();
    }

    @PostConstruct
    //postConstruct porque se não o @Value fica nulo caso monte o algorith antes de o spring injetar o valor
    public Algorithm getAlgorithm() {
        if (this.algorithm == null) {
            this.algorithm = Algorithm.HMAC256(secret);
        }
        return algorithm;
    }
}
