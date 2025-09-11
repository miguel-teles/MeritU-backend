package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.GoalRQDTO;
import io.meritu.meritubackend.domain.dto.GoalRSDTO;
import io.meritu.meritubackend.domain.entity.Goal;
import io.meritu.meritubackend.exception.GoalNotFoundException;
import io.meritu.meritubackend.service.goal.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping
    public ResponseEntity<List<GoalRSDTO>> getAll() {
        return ResponseEntity.ok(goalService.findAll().stream().map(Goal::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalRSDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.findById(id).map(Goal::toDTO).orElseThrow(()->new GoalNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<GoalRSDTO> save(@RequestBody @Valid GoalRQDTO goalDTO) {
        return ResponseEntity.ok(goalService.save(new Goal(goalDTO)).toDTO());
    }

}
