package io.meritu.meritubackend;

import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.TeamGoalRQDTO;
import io.meritu.meritubackend.domain.entity.*;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.exception.InvalidOperationGoalException;
import io.meritu.meritubackend.service.employee.EmployeeService;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class IndividualGoalOperationServiceTest {

    private GoalOperationService service;

    @Mock
    private GoalService goalService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private GoalAsyncOperationHelper goalAsyncOperationHelper;

    User user = null;

    @BeforeEach
    void beforeEach() {
        mockIndividualGoalCompletionWithoutCompletingTeamGoal();
        mockIndividualGoalCompletionWithNonExistingGoal();
        mockIndividualGoalCompletionAlreadyCompleted();
        mockUser();

        service = new IndividualGoalOperationService(goalService,
                employeeService,
                goalAsyncOperationHelper);
    }

    private void mockIndividualGoalCompletionWithNonExistingGoal() {
        when(goalService.findById(2l)).thenReturn(Optional.empty());
    }

    private void mockIndividualGoalCompletionAlreadyCompleted() {
        IndividualGoal individualGoal = montaIndividualGoal();

        individualGoal.complete();

        when(goalService.findById(3l)).thenReturn(Optional.of(individualGoal));
        when(goalService.save(any())).thenReturn(individualGoal);
    }

    private static IndividualGoal montaIndividualGoal() {
        TeamGoal teamGoal = new TeamGoal(new TeamGoalRQDTO(20, 20, "team teste", 10, 1l, 1l));
        IndividualGoal individualGoal = new IndividualGoal(new IndividualGoalRQDTO(10,
                1l,
                "individual teste",
                10,
                1l));
        individualGoal.setTeamGoal(teamGoal);
        teamGoal.setTeamMemberGoals(List.of(individualGoal));
        return individualGoal;
    }

    private void mockIndividualGoalCompletionWithoutCompletingTeamGoal() {
        IndividualGoal individualGoal = montaIndividualGoal();

        when(goalService.findById(1l)).thenReturn(Optional.of(individualGoal));
        when(goalService.save(any())).thenReturn(individualGoal);
    }

    private void mockUser() {
        user = new User("teste", "teste", UserRole.COMMON, new Employee("a", "a", true, Role.EMPLOYEE));
        when(employeeService.findById(1l)).thenReturn(Optional.of(user.getEmployee()));
    }

    @Test
    void completeGoalTest() {
        IndividualGoal goal = (IndividualGoal) service.completeGoal(1l);

        Assertions.assertTrue(goal.isAchieved());
        Assertions.assertNotNull(user.getEmployee().getBalance());
        Assertions.assertTrue(user.getEmployee().getBalance() > 0);
        verify(goalAsyncOperationHelper).completeTeamGoalIfPointsReached(goal.getTeamGoal());
    }

    @Test
    void completeNonExistingGoalTest() {
        assertThrows(GoalNotFoundException.class, ()->service.completeGoal(2l));
    }

    @Test
    void completeAlreadyCompletedGoalTest() {
        assertThrows(InvalidOperationGoalException.class, ()->service.completeGoal(3l));
    }


}
