package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.AddEmployeeToTeamRQDTO;
import io.meritu.meritubackend.domain.dto.EmployeeRSDTO;
import io.meritu.meritubackend.domain.dto.TeamDTO;
import io.meritu.meritubackend.domain.entity.Employee;
import io.meritu.meritubackend.service.employee.EmployeeService;
import io.meritu.meritubackend.service.team.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/team")
    public ResponseEntity<List<EmployeeRSDTO>> addEmployees(@RequestBody @Valid AddEmployeeToTeamRQDTO rqDTO) {
        return ResponseEntity.ok(employeeService.addEmployees(rqDTO.getEmployeesIds(), rqDTO.getTeamId()).stream().map(Employee::toDTO).toList());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeRSDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees().stream().map(Employee::toDTO).toList());
    }
}
