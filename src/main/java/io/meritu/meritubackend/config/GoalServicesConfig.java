package io.meritu.meritubackend.config;

import io.meritu.meritubackend.config.annotation.IndividualGoal;
import io.meritu.meritubackend.config.annotation.IndividualGoalOperation;
import io.meritu.meritubackend.config.annotation.TeamGoal;
import io.meritu.meritubackend.config.annotation.TeamGoalOperation;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.goal.impl.IndividualGoalOperationService;
import io.meritu.meritubackend.service.goal.impl.IndividualGoalService;
import io.meritu.meritubackend.service.goal.impl.TeamGoalOperationServiceImpl;
import io.meritu.meritubackend.service.goal.impl.TeamGoalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoalServicesConfig {

    @IndividualGoal
    @Bean(name = "IndividualGoal")
    public GoalService individualGoalService(IndividualGoalService obj) {
        return obj;
    }

    @TeamGoal
    @Bean(name = "TeamGoal")
    public GoalService TeamGoalService(TeamGoalService obj) {
        return obj;
    }

    @IndividualGoalOperation
    @Bean(name = "IndividualGoalOperation")
    public GoalOperationService getIndividualGoalOperationService(IndividualGoalOperationService obj) {
        return obj;
    }

    @TeamGoalOperation
    @Bean(name = "TeamGoalOperation")
    public GoalOperationService getTeamGoalOperationService(TeamGoalOperationServiceImpl obj) {
        return obj;
    }

}
