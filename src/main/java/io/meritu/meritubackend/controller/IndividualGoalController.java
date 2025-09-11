package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.IndividualGoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.domain.entity.IndividualGoal;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.service.goal.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goals/individual")
@RequiredArgsConstructor
public class IndividualGoalController {
    private final GoalService<IndividualGoal> individualGoalService;

    @GetMapping
    public ResponseEntity<List<GoalRSDTO>> getAll() {
        return ResponseEntity.ok(individualGoalService.findAll().stream().map(IndividualGoal::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalRSDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(individualGoalService.findById(id).map(IndividualGoal::toDTO).orElseThrow(()->new GoalNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<GoalRSDTO> save(@RequestBody @Valid IndividualGoalRQDTO goalDTO) {
        return ResponseEntity.ok(individualGoalService.save(new IndividualGoal(goalDTO)).toDTO());
    }
}
