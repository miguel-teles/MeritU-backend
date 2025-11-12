package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.UserRQDTO;
import io.meritu.meritubackend.domain.dto.UserRSDTO;
import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.domain.entity.Role;
import io.meritu.meritubackend.domain.entity.User;
import io.meritu.meritubackend.domain.entity.UserRole;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserRSDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id)
                .map(User::toDTO)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserRSDTO>> getAll() {
        return ResponseEntity.ok(userService.findAll().stream()
                .map(User::toDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<UserRSDTO> save(@RequestBody @Valid UserRQDTO userRQDTO) {
        return ResponseEntity.ok(userService.save(new User(userRQDTO.getUsername(),
                userRQDTO.getPassword(),
                UserRole.fromName(userRQDTO.getRole()),
                new Employee(userRQDTO.getEmployee()))).toDTO());
    }

}
