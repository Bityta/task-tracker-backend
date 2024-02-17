package ru.app.restapiservice.model.mapper;

import org.springframework.stereotype.Component;
import ru.app.restapiservice.model.Task;
import ru.app.restapiservice.model.dto.task.TaskDto;
import ru.app.restapiservice.model.dto.task.TaskDtoView;

@Component
public class TaskMapper {

    public TaskDtoView map(Task task) {

        return TaskDtoView.builder()
                .id(task.getId())
                .header(task.getHeader())
                .description(task.getDescription())
                .isCompleted(task.isCompleted())
                .dateCompleted(task.getDateCompleted())
                .build();

    }

    public Task map(TaskDto taskDto) {

        return Task.builder()
                .header(taskDto.getHeader())
                .description(taskDto.getDescription())
                .build();

    }
}
