package io.meritu.meritubackend;

import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.TeamGoalRQDTO;
import io.meritu.meritubackend.domain.entity.*;
import io.meritu.meritubackend.domain.entity.enums.Role;
import io.meritu.meritubackend.domain.entity.enums.UserRole;
import io.meritu.meritubackend.repo.*;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class PopulaBanco {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;
    private final IndividualGoalRepository individualGoalRepository;
    private final TeamGoalRepository teamGoalRepository;
    private final GoalOperationService goalOperationService;

    public PopulaBanco(UserRepository userRepository,
                       TeamRepository teamRepository,
                       EmployeeRepository employeeRepository,
                       IndividualGoalRepository individualGoalRepository,
                       TeamGoalRepository teamGoalRepository,
                       @io.meritu.meritubackend.config.annotation.IndividualGoalOperation GoalOperationService goalOperationService) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.individualGoalRepository = individualGoalRepository;
        this.teamGoalRepository = teamGoalRepository;
        this.goalOperationService = goalOperationService;
    }

    @PostConstruct
    public void postConstruct() {
        userRepository.deleteAll();
        individualGoalRepository.deleteAll();
        teamGoalRepository.deleteAll();

        List<Team> list = teamRepository.findAll().stream().peek(team -> team.setManager(null)).toList();
        teamRepository.saveAll(list);

        employeeRepository.deleteAll();
        teamRepository.deleteAll();

        Employee miguel = new Employee("Miguel", "Teles", "miguel@email.com", true, Role.EMPLOYEE);
        Employee joao = new Employee("João", "Gomes", "joao@email.com", true, Role.EMPLOYEE);
        Employee maria = new Employee("Maria", "Gracieli", "maria@email.com", true, Role.MANAGER);

        User contaMiguel = new User("miguel",
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"),
                UserRole.COMMON,
                miguel);
        User contaJoao = new User("joao",
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"),
                UserRole.COMMON,
                joao);
        User contaMaria = new User("maria",
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"),
                UserRole.COMMON,
                maria);

        employeeRepository.saveAll(List.of(miguel, joao, maria));

        userRepository.saveAll(List.of(contaMiguel, contaJoao, contaMaria));

        Team team = new Team("Team 1", contaMaria.getEmployee());
        teamRepository.save(team);

        contaMiguel.getEmployee().setTeam(team);
        employeeRepository.save(contaMiguel.getEmployee());

        contaJoao.getEmployee().setTeam(team);
        employeeRepository.save(contaJoao.getEmployee());

        createGoalsAndCompleteSome(team, miguel, joao, maria);
        createGoalsAndCompleteSome(team, miguel, joao, maria);
        createGoalsAndCompleteSome(team, miguel, joao, maria);
    }

    private void createGoalsAndCompleteSome(Team team,
                                            Employee miguel,
                                            Employee joao,
                                            Employee maria) {
        TeamGoal teamGoal = new TeamGoal(new TeamGoalRQDTO(20,
                null,
                "Team goal",
                "Meta de time muito importante e legal. Relacionada aquela tarefa também importante.",
                10,
                team.getId(),
                null,
                LocalDateTime.of(2025, 12, 30, 11, 0)));
        teamGoalRepository.save(teamGoal);

        IndividualGoal individualGoal1 = adicionaIndividualGoalUsuario(miguel.getId(), teamGoal.getId());
        IndividualGoal individualGoal2 = adicionaIndividualGoalUsuario(miguel.getId(), teamGoal.getId());
        adicionaIndividualGoalUsuario(miguel.getId(), teamGoal.getId());
        IndividualGoal individualGoal3 = adicionaIndividualGoalUsuario(joao.getId(), teamGoal.getId());
        adicionaIndividualGoalUsuario(joao.getId(), teamGoal.getId());
        adicionaIndividualGoalUsuario(joao.getId(), teamGoal.getId());
        adicionaIndividualGoalUsuario(maria.getId(), teamGoal.getId());

        goalOperationService.completeGoal(individualGoal1.getId());
        goalOperationService.completeGoal(individualGoal2.getId());
        goalOperationService.completeGoal(individualGoal3.getId());
    }

    //TODO: TIRAR O H2 E CRIAR UM BANCO NO DOCKER MESMO, FODA-SE!!!!

    private IndividualGoal adicionaIndividualGoalUsuario(long goalOwnerId, long teamGoalId) {
        IndividualGoal individualGoal = new IndividualGoal(new IndividualGoalRQDTO(randomDecimal(),
                teamGoalId,
                "Individual goal",
                "Descrição da goal individual muito legal. É preciso terminá-la até a deadline.",
                10,
                goalOwnerId,
                LocalDateTime.of(2026, 2, 1, 15, 30)));
        individualGoalRepository.save(individualGoal);
        return individualGoal;
    }

    int randomDecimal() {
        Random r = new Random();
        return (r.nextInt((int)((99-10)*10+1))+10*10) / 10;
    }

}