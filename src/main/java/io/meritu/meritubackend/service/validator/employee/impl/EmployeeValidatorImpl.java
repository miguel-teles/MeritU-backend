package io.meritu.meritubackend.service.validator.employee.impl;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.exception.InvalidEmployeeException;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.service.validator.employee.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidatorImpl implements EmployeeValidator {

    @Override
    public void validateUpdate(Employee employee) {
        if (employee.getId() == null) {
            throw new InvalidEmployeeException("Impossible to update not created employee");
        }
    }
}
