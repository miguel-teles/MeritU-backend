package io.meritu.meritubackend;

import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.TeamGoalRQDTO;
import io.meritu.meritubackend.domain.entity.*;
import io.meritu.meritubackend.repo.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulaBanco {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;
    private final IndividualGoalRepository individualGoalRepository;
    private final TeamGoalRepository teamGoalRepository;

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

        contaMiguel.getEmployee().setTeam(team);
        employeeRepository.save(contaMiguel.getEmployee());

        contaJoao.getEmployee().setTeam(team);
        employeeRepository.save(contaJoao.getEmployee());

        TeamGoal goal = new TeamGoal(new TeamGoalRQDTO(20, 10, "Team goal", 10, 1l, null));
        teamGoalRepository.save(goal);

        IndividualGoal individualGoal = new IndividualGoal(new IndividualGoalRQDTO(20, 1l, "Individual goal", 10, 1l));
        individualGoalRepository.save(individualGoal);
    }

}