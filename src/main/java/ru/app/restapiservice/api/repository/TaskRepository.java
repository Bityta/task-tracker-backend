package ru.app.restapiservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.restapiservice.api.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> getByOwner_Email(String email);
}
