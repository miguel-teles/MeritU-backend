package io.meritu.meritubackend.service.team;

import io.meritu.meritubackend.domain.dto.TeamDTO;
import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Team;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

public interface TeamService {
    Team save(Team team);

    List<Team> findAll();

    Optional<Team> findById(Long id);
}
