package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.AddEmployeeToTeamRQDTO;
import io.meritu.meritubackend.domain.dto.EmployeeRSDTO;
import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.exception.EmployeeNotFoundException;
import io.meritu.meritubackend.service.employee.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employees", description = "Endpoints for managing employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/team")
    @Operation(summary = "Add employees to a team")
    public ResponseEntity<List<EmployeeRSDTO>> addEmployees(@RequestBody @Valid AddEmployeeToTeamRQDTO rqDTO) {
        return ResponseEntity.ok(employeeService.addEmployees(rqDTO.getEmployeesIds(), rqDTO.getTeamId()).stream().map(Employee::toDTO).toList());
    }

    @GetMapping
    @Operation(summary = "Get all employees")
    public ResponseEntity<List<EmployeeRSDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees().stream().map(Employee::toDTO).toList());
    }

    @GetMapping("/best")
    @Operation(summary = "Get the employees with the best performances")
    public ResponseEntity<List<EmployeeRSDTO>> getBestPerformancesEmployees(@RequestParam Integer limit) {
        return ResponseEntity.ok(employeeService.getBestPerformanceEmployees(limit).stream().map(Employee::toDTO).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get by Id")
    public ResponseEntity<EmployeeRSDTO> getById(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(em -> ResponseEntity.ok(em.toDTO()))
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
