package io.meritu.meritubackend.service.validator.team.impl;

import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Team;
import io.meritu.meritubackend.exception.InvalidOperationTeamException;
import io.meritu.meritubackend.repo.EmployeeRepository;
import io.meritu.meritubackend.repo.TeamRepository;
import io.meritu.meritubackend.service.validator.team.TeamValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamValidatorImpl implements TeamValidator {

        private final TeamRepository teamRepository;

    @Override
    public void validatePersist(Team entity) {
    }

    @Override
    public void validateUpdate(Team entity) {
    }
}
