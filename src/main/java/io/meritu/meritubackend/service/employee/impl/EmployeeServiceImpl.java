package io.meritu.meritubackend.service.employee.impl;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Team;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.exception.TeamNotFoundException;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.service.employee.EmployeeService;
import io.meritu.meritubackend.service.team.TeamService;
import io.meritu.meritubackend.service.validator.employee.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeValidator employeeValidator;
    private final TeamService teamService;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employeeValidator.validateUpdate(employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> addEmployees(List<Long> employeesIds, Long teamId) {
        Team team = teamService.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));

        List<Employee> employees = new ArrayList<>();
        for (Long employeesId : employeesIds) {
            Employee employee = employeeRepository.findById(employeesId).orElseThrow(() -> new EmployeeNotFoundException(employeesId));
            employee.setTeam(team);
            employees.add(employee);
        }
        return employees;
    }
}
