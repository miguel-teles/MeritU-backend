package io.meritu.meritubackend.repo;

import io.meritu.meritubackend.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.individualGoals")
    List<Employee> findAllWithGoals();

    @Query("SELECT e FROM Employee e " +
            "LEFT JOIN e.individualGoals g WITH g.statusValue = 2 " + //COMPLETED
            "GROUP BY e.id " +
            "ORDER BY COUNT(g) DESC LIMIT :limit")
    List<Employee> findTopByCompletedGoals(Integer limit);

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.individualGoals WHERE e.id = :id")
    Optional<Employee> findByIdWithGoals(Long id);
}
