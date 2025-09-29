package io.meritu.meritubackend;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Team;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.service.employee.EmployeeService;
import io.meritu.meritubackend.service.employee.impl.EmployeeServiceImpl;
import io.meritu.meritubackend.service.team.TeamService;
import io.meritu.meritubackend.service.validator.employee.EmployeeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeValidator employeeValidator;
    @Mock
    private TeamService teamService;


    private Long teamId = 1L;
    private Long employeeId1 = 1L;
    private Long employeeId2 = 2L;

    @BeforeEach
    void beforeAll() {
        mockAddEmployeeTest();

        employeeService = new EmployeeServiceImpl(employeeRepository,
                employeeValidator,
                teamService);
    }

    private void mockAddEmployeeTest() {
        when(teamService.findById(teamId)).thenReturn(Optional.of(new Team(teamId)));
        when(employeeRepository.findById(employeeId1)).thenReturn(Optional.of(new Employee(employeeId1)));
        when(employeeRepository.findById(employeeId2)).thenReturn(Optional.of(new Employee(employeeId2)));
    }

    @Test
    void addEmployeesTest() {
        List<Employee> employees = employeeService.addEmployees(List.of(1l, 2l), teamId);

        for (Employee employee : employees) {
            Assertions.assertNotNull(employee.getTeam());
            Assertions.assertNotNull(employee.getTeam().getId());
        }
    }

}
