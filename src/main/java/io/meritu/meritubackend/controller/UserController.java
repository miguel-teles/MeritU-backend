package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.UserRQDTO;
import io.meritu.meritubackend.domain.dto.UserRSDTO;
import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.domain.entity.enums.UserRole;
import io.meritu.meritubackend.exception.UserNotFoundException;
import io.meritu.meritubackend.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<UserRSDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id)
                .map(User::toDTO)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserRSDTO>> getAll() {
        return ResponseEntity.ok(userService.findAll().stream()
                .map(User::toDTO)
                .toList());
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserRSDTO> save(@RequestBody @Valid UserRQDTO userRQDTO) {
        return ResponseEntity.ok(userService.save(new User(userRQDTO.getUsername(),
                userRQDTO.getPassword(),
                UserRole.fromName(userRQDTO.getRole()),
                new Employee(userRQDTO.getEmployee()))).toDTO());
    }

}
