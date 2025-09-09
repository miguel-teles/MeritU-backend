package io.meritu.meritubackend.service.validator.employee;

import io.meritu.meritubackend.domain.entity.Employee;

public interface EmployeeValidator {
    void validateUpdate(Employee employee);
}
