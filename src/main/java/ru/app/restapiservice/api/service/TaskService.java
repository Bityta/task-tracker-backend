package ru.app.restapiservice.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.api.model.Task;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing tasks.
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Adds a task for the specified user.
     *
     * @param user The user for whom the task is being added.
     * @param task The task to be added.
     */
    @Transactional
    public void addTask(User user, Task task) {
        if (user.getTasks() == null) {
            user.setTasks(new ArrayList<>());
        }
        user.getTasks().add(task);
        task.setOwner(user);
        this.taskRepository.save(task);
    }

    /**
     * Returns a list of tasks for the user with the specified email.
     *
     * @param email The email of the user.
     * @return The list of user tasks.
     */
    public List<Task> getByOwnerEmail(String email) {
        return this.taskRepository.getByOwner_Email(email);
    }
}
