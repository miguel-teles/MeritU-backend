package io.meritu.meritubackend.service.goal;

import java.util.List;
import java.util.Optional;

public interface GoalService<T> {

    T save(T goal);

    List<T> findAll();

    Optional<T> findById(Long id);

}
