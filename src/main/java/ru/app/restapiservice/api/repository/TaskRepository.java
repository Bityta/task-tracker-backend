package ru.app.restapiservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.restapiservice.api.model.Task;

import java.util.List;

/**
 * Repository interface for managing tasks.
 * Provides CRUD operations for Task entities in the database.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Retrieves a list of tasks by the email of the task owner.
     *
     * @param email the email of the task owner
     * @return a list of tasks owned by the specified email
     */
    List<Task> getByOwner_Email(String email);
}
