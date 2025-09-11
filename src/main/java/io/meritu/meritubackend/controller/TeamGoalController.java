package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.dto.TeamGoalRQDTO;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.TeamGoal;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.service.goal.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goals/team")
@RequiredArgsConstructor
public class TeamGoalController {
    private final GoalService<TeamGoal> teamGoalService;

    @GetMapping
    public ResponseEntity<List<GoalRSDTO>> getAll() {
        return ResponseEntity.ok(teamGoalService.findAll().stream().map(TeamGoal::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalRSDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teamGoalService.findById(id).map(TeamGoal::toDTO).orElseThrow(()->new GoalNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<GoalRSDTO> save(@RequestBody @Valid TeamGoalRQDTO goalDTO) {
        return ResponseEntity.ok(teamGoalService.save(new TeamGoal(goalDTO)).toDTO());
    }

}
