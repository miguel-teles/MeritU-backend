package io.meritu.meritubackend;

import io.meritu.meritubackend.domain.entity.Role;
import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulaBanco {

    private final UserRepository userRepository;

    @PostConstruct
    public void postConstruct() {

        User contaMiguel = new User("miguel", new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"), Role.EMPLOYEE);
        User contaJoao = new User("joao", new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"), Role.EMPLOYEE);
        User contaMaria = new User("maria", new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"), Role.MANAGER);

        userRepository.saveAll(List.of(contaMiguel, contaJoao, contaMaria));
    }

}