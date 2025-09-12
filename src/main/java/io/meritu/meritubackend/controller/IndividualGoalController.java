package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.config.annotation.IndividualGoalOperation;
import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.service.goal.GoalOperationService;
import io.meritu.meritubackend.service.goal.GoalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goals/individual")
public class IndividualGoalController {

    private final GoalService goalService;
    private final GoalOperationService goalOperationService;

    public IndividualGoalController(@io.meritu.meritubackend.config.annotation.IndividualGoal GoalService goalService,
                                    @IndividualGoalOperation GoalOperationService goalOperationService) {
        this.goalService = goalService;
        this.goalOperationService = goalOperationService;
    }

    @GetMapping
    public ResponseEntity<List<GoalRSDTO>> getAll() {
        return ResponseEntity.ok(goalService.findAll().stream().map(Goal::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalRSDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.findById(id).map(Goal::toDTO).orElseThrow(()->new GoalNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<GoalRSDTO> save(@RequestBody @Valid IndividualGoalRQDTO goalDTO) {
        return ResponseEntity.ok(goalService.save(new IndividualGoal(goalDTO)).toDTO());
    }

    @PostMapping("/complete/{goalId}")
    public ResponseEntity<GoalRSDTO> complete(@PathVariable @NotNull Long goalId) {
        return ResponseEntity.ok(goalOperationService.completeGoal(goalId).toDTO());
    }
}
