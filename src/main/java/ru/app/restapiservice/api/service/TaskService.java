package ru.app.restapiservice.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.api.model.Task;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public void addTask(User user, Task task) {
        if (user.getTasks() == null) {
            user.setTasks(new ArrayList<>());
        }
        user.getTasks().add(task);
        task.setOwner(user);
        this.taskRepository.save(task);
    }

    public List<Task> getByOwnerEmail(String email) {
        return this.taskRepository.getByOwner_Email(email);
    }

}
