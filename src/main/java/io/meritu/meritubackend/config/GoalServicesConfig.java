package io.meritu.meritubackend.config;

import io.meritu.meritubackend.config.annotation.IndividualGoal;
import io.meritu.meritubackend.config.annotation.TeamGoal;
import io.meritu.meritubackend.repo.GoalRepository;
import io.meritu.meritubackend.repo.IndividualGoalRepository;
import io.meritu.meritubackend.repo.TeamGoalRepository;
import io.meritu.meritubackend.service.employee.EmployeeService;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.goal.impl.IndividualGoalOperationService;
import io.meritu.meritubackend.service.goal.impl.IndividualGoalService;
import io.meritu.meritubackend.service.goal.impl.TeamGoalOperationService;
import io.meritu.meritubackend.service.goal.impl.TeamGoalService;
import io.meritu.meritubackend.service.resolver.goal.GoalValidatorResolver;
import io.meritu.meritubackend.service.team.TeamService;
import io.meritu.meritubackend.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoalServicesConfig {

    @Bean(name = "IndividualGoal")
    public GoalService individualGoalService(IndividualGoalRepository individualGoalRepository,
                                             GoalValidatorResolver goalValidatorResolver) {
        return new IndividualGoalService(individualGoalRepository,
                goalValidatorResolver);
    }

    @Bean(name = "TeamGoal")
    public GoalService TeamGoalService(TeamGoalRepository teamGoalRepository,
                                       GoalValidatorResolver goalValidatorResolver) {
        return new TeamGoalService(teamGoalRepository,
                goalValidatorResolver);
    }

    @Bean(name = "IndividualGoalOperation")
    public GoalOperationService getIndividualGoalOperationService(@IndividualGoal GoalService goalService,
                                                                  UserService userService) {
        return new IndividualGoalOperationService(goalService,
                userService);
    }

    @Bean(name = "TeamGoalOperation")
    public GoalOperationService getTeamGoalOperationService(@TeamGoal GoalService goalService,
                                                            UserService userService,
                                                            TeamService teamService) {
        return new TeamGoalOperationService(goalService,
                userService,
                teamService);
    }

}
