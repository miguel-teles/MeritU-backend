package io.meritu.meritubackend;

import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.goal.impl.IndividualGoalOperationService;
import io.meritu.meritubackend.service.goal.impl.TeamAndIndividualGoalOperationHelper;
import io.meritu.meritubackend.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class IndividualGoalOperationServiceTest {

    private GoalOperationService service;

    @Mock
    private GoalService goalService;
    @Mock
    private UserService userService;
    @Mock
    private TeamAndIndividualGoalOperationHelper teamAndIndividualGoalOperationHelper;

    @BeforeEach
    void beforeEach() {
        when(goalService.findById(1l)).thenReturn(Optional.of(new IndividualGoal(1l)));

        service = new IndividualGoalOperationService(goalService,
                userService,
                teamAndIndividualGoalOperationHelper);
    }

    @Test
    void completeGoalTest() {

    }
}
