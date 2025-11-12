package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.config.annotation.TeamGoalOperation;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.dto.TeamGoalRQDTO;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.TeamGoal;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goals/team")
@Tag(name = "Team Goals", description = "Endpoints for managing team goals")
public class TeamGoalController {
    private final GoalService goalService;
    private final GoalOperationService goalOperationService;

    public TeamGoalController(@io.meritu.meritubackend.config.annotation.TeamGoal GoalService goalService,
                              @TeamGoalOperation GoalOperationService goalOperationService) {
        this.goalService = goalService;
        this.goalOperationService = goalOperationService;
    }

    @GetMapping
    @Operation(summary = "Get all team goals")
    public ResponseEntity<List<GoalRSDTO>> getAll() {
        return ResponseEntity.ok(goalService.findAll().stream().map(Goal::toDTO).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a team goal by ID")
    public ResponseEntity<GoalRSDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.findById(id).map(Goal::toDTO).orElseThrow(() -> new GoalNotFoundException(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new team goal")
    public ResponseEntity<GoalRSDTO> save(@RequestBody @Valid TeamGoalRQDTO goalDTO) {
        TeamGoal teamGoal = (TeamGoal) goalService.save(new TeamGoal(goalDTO));
        return ResponseEntity.ok(teamGoal.toDTO());
    }

    @PostMapping("/complete/{goalId}")
    @Operation(summary = "Mark a team goal as complete")
    public ResponseEntity<GoalRSDTO> complete(@PathVariable @NotNull Long goalId) {
        return ResponseEntity.ok(goalOperationService.completeGoal(goalId).toDTO());
    }

}
