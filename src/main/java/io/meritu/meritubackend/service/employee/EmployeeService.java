package io.meritu.meritubackend.service.employee;

import io.meritu.meritubackend.domain.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    List<Employee> getBestPerformanceEmployees(Integer limit);
    Employee updateEmployee(Employee employee);
    Optional<Employee> findById(Long id);
    List<Employee> addEmployees(List<Long> employeesIds, Long teamId);
    Employee save(Employee employee);
}
