package ru.app.restapiservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.restapiservice.api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
