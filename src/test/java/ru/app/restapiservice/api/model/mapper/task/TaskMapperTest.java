package ru.app.restapiservice.api.model.mapper.task;

import org.junit.jupiter.api.Test;
import ru.app.restapiservice.api.model.Task;
import ru.app.restapiservice.api.model.dto.task.TaskDto;
import ru.app.restapiservice.api.model.dto.task.TaskDtoView;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    public void testMapToTaskDtoView() {
        Task task = Task.builder()
                .id(1)
                .header("Header")
                .description("Description")
                .isCompleted(false)
                .dateCompleted(LocalDate.now())
                .build();

        TaskMapper taskMapper = new TaskMapper();
        TaskDtoView taskDtoView = taskMapper.map(task);

        assertEquals(task.getId(), taskDtoView.getId());
        assertEquals(task.getHeader(), taskDtoView.getHeader());
        assertEquals(task.getDescription(), taskDtoView.getDescription());
        assertEquals(task.isCompleted(), taskDtoView.isCompleted());
        assertEquals(task.getDateCompleted(), taskDtoView.getDateCompleted());
    }

    @Test
    public void testMapToTask() {
        TaskDto taskDto = TaskDto.builder()
                .header("Header")
                .description("Description")
                .build();

        TaskMapper taskMapper = new TaskMapper();
        Task task = taskMapper.map(taskDto);

        assertEquals(taskDto.getHeader(), task.getHeader());
        assertEquals(taskDto.getDescription(), task.getDescription());
    }
}
