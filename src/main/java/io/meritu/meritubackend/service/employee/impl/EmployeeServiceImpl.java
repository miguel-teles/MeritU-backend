package io.meritu.meritubackend.service.employee.impl;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.service.employee.EmployeeService;
import io.meritu.meritubackend.service.validator.employee.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeValidator employeeValidator;

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
}
