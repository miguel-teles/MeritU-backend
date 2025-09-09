package io.meritu.meritubackend.config;

import io.meritu.meritubackend.service.login.TokenService;
import io.meritu.meritubackend.service.login.impl.TokenServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

    @Bean
    public TokenService tokenService() {
        return new TokenServiceImpl();
    }

}
