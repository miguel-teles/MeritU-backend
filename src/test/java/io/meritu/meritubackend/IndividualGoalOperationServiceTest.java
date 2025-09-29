package io.meritu.meritubackend;

import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.TeamGoalRQDTO;
import io.meritu.meritubackend.domain.entity.*;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.meritu.meritubackend.service.goal.impl.IndividualGoalOperationService;
import io.meritu.meritubackend.service.goal.impl.GoalAsyncOperationHelper;
import io.meritu.meritubackend.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class IndividualGoalOperationServiceTest {

    private GoalOperationService service;

    @Mock
    private GoalService goalService;
    @Mock
    private UserService userService;
    @Mock
    private GoalAsyncOperationHelper goalAsyncOperationHelper;

    User user = null;

    @BeforeEach
    void beforeEach() {
        mockIndividualGoalCompletionWithoutCompletingTeamGoal();

        mockUser();

        service = new IndividualGoalOperationService(goalService,
                userService,
                goalAsyncOperationHelper);
    }

    private void mockIndividualGoalCompletionWithoutCompletingTeamGoal() {
        TeamGoal teamGoal = new TeamGoal(new TeamGoalRQDTO(20, 20, "team teste", 10, 1l, 1l));
        IndividualGoal individualGoal = new IndividualGoal(new IndividualGoalRQDTO(10,
                1l,
                "individual teste",
                10,
                1l));
        individualGoal.setTeamGoal(teamGoal);
        teamGoal.setTeamMemberGoals(List.of(individualGoal));

        when(goalService.findById(1l)).thenReturn(Optional.of(individualGoal));
        when(goalService.save(any())).thenReturn(individualGoal);
    }

    private void mockUser() {
        user = new User("teste", "teste", Role.EMPLOYEE, new Employee("a", "a", true));
        when(userService.findByEmployeeId(1l)).thenReturn(Optional.of(user));
    }

    @Test
    void completeGoalTest() {
        IndividualGoal goal = (IndividualGoal) service.completeGoal(1l);

        Assertions.assertTrue(goal.isAchieved());
        Assertions.assertNotNull(user.getBalance());
        Assertions.assertTrue(user.getBalance() > 0);
        verify(goalAsyncOperationHelper).completeTeamGoalIfPointsReached(goal.getTeamGoal());

    }
}
