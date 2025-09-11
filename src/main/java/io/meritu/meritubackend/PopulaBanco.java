package io.meritu.meritubackend;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Role;
import io.meritu.meritubackend.domain.entity.Team;
import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.repo.TeamRepository;
import io.meritu.meritubackend.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulaBanco {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @PostConstruct
    public void postConstruct() {
        User contaMiguel = new User("miguel",
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"),
                Role.EMPLOYEE,
                new Employee("Miguel", "Teles", true));
        User contaJoao = new User("joao",
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"),
                Role.EMPLOYEE,
                new Employee("João", "Gomes", true));
        User contaMaria = new User("maria",
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"),
                Role.MANAGER,
                new Employee("Maria", "Gracieli", true));

        userRepository.saveAll(List.of(contaMiguel, contaJoao, contaMaria));

        Team team = new Team("Team 1", contaMaria.getEmployee());
        teamRepository.save(team);
    }

}