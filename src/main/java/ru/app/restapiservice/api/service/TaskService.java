package ru.app.restapiservice.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.api.model.Task;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Transactional
    public void addTask(String email, Task task) {
        User user = this.userService.findByEmail(email);
        user.addTask(task);
        task.setOwner(user);
        this.taskRepository.save(task);
        this.userService.save(user);
    }
    public List<Task> getTasks(String email) {
        return this.userService.findByEmail(email).getTasks();
    }

}
