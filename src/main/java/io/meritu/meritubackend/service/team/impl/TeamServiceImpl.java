package io.meritu.meritubackend.service.team.impl;

import io.meritu.meritubackend.domain.entity.Team;
import io.meritu.meritubackend.repo.TeamRepository;
import io.meritu.meritubackend.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }
}
