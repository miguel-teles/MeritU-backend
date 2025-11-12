package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.config.annotation.IndividualGoalOperation;
import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goals/individual")
@Tag(name = "Individual Goals", description = "Endpoints for managing individual goals")
public class IndividualGoalController {

    private final GoalService goalService;
    private final GoalOperationService goalOperationService;

    public IndividualGoalController(@io.meritu.meritubackend.config.annotation.IndividualGoal GoalService goalService,
                                    @IndividualGoalOperation GoalOperationService goalOperationService) {
        this.goalService = goalService;
        this.goalOperationService = goalOperationService;
    }

    @GetMapping
    @Operation(summary = "Get all individual goals")
    public ResponseEntity<List<GoalRSDTO>> getAll() {
        return ResponseEntity.ok(goalService.findAll().stream().map(Goal::toDTO).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an individual goal by ID")
    public ResponseEntity<GoalRSDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.findById(id).map(Goal::toDTO).orElseThrow(()->new GoalNotFoundException(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new individual goal")
    public ResponseEntity<GoalRSDTO> save(@RequestBody @Valid IndividualGoalRQDTO goalDTO) {
        return ResponseEntity.ok(goalService.save(new IndividualGoal(goalDTO)).toDTO());
    }

    @PostMapping("/complete/{goalId}")
    @Operation(summary = "Mark an individual goal as complete")
    public ResponseEntity<GoalRSDTO> complete(@PathVariable @NotNull Long goalId) {
        return ResponseEntity.ok(goalOperationService.completeGoal(goalId).toDTO());
    }
}
