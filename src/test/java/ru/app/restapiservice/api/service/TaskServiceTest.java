package ru.app.restapiservice.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.app.restapiservice.api.model.Task;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.repository.TaskRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void getByOwner_TwoTask() {
        String email = "test@gmail.com";
        Task task1 = Task.builder()
                .header("Test1")
                .build();

        Task task2 = Task.builder()
                .header("Test1")
                .build();

        User user = User.builder()
                .email(email)
                .password("test")
                .firstName("test")
                .tasks(List.of(task1, task2))
                .build();

        task1.setOwner(user);
        task2.setOwner(user);

        when(this.taskRepository.getByOwner_Email(email)).thenReturn(List.of(task1, task2));
        assertEquals(List.of(task1, task2), this.taskService.getByOwnerEmail(email));
        verify(this.taskRepository, times(1)).getByOwner_Email(email);


    }

    @Test
    public void getByOwner_OneTask() {
        String email = "test@gmail.com";
        Task task1 = Task.builder()
                .header("Test1")
                .build();


        User user = User.builder()
                .email(email)
                .password("test")
                .firstName("test")
                .tasks(List.of(task1))
                .build();

        task1.setOwner(user);

        when(this.taskRepository.getByOwner_Email(email)).thenReturn(List.of(task1));
        assertEquals(List.of(task1), this.taskService.getByOwnerEmail(email));
        verify(this.taskRepository, times(1)).getByOwner_Email(email);


    }

    @Test
    public void getByOwner_NoTask() {
        String email = "test@gmail.com";
        when(this.taskRepository.getByOwner_Email(email)).thenReturn(List.of());
        assertEquals(List.of(), this.taskService.getByOwnerEmail(email));
        verify(this.taskRepository, times(1)).getByOwner_Email(email);


    }

    @Test
    public void addTask_TwoTask() {
        Task task = Task.builder()
                .header("Test")
                .build();

        User user = User.builder()
                .email("test@gmail.com")
                .password("test")
                .firstName("test")
                .build();

        when(this.taskRepository.save(task)).thenReturn(task);
        this.taskService.addTask(user, task);
        when(this.taskRepository.save(task)).thenReturn(task);
        this.taskService.addTask(user, task);
        verify(this.taskRepository, times(2)).save(task);


    }
}