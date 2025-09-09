package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.TeamDTO;
import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Team;
import io.meritu.meritubackend.exception.TeamNotFoundException;
import io.meritu.meritubackend.service.team.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDTO> save(@Valid @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.save(new Team(teamDTO.getName(), new Employee(teamDTO.getManagerId())))
                .toDTO());
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAll() {
        return ResponseEntity.ok(teamService.findAll().stream().map(Team::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.findById(id).map(Team::toDTO).orElseThrow(()->new TeamNotFoundException(id)));
    }


}
