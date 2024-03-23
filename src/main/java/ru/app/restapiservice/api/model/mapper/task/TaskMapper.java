package ru.app.restapiservice.api.model.mapper.task;

import org.springframework.stereotype.Component;
import ru.app.restapiservice.api.model.Task;
import ru.app.restapiservice.api.model.dto.task.TaskDto;
import ru.app.restapiservice.api.model.dto.task.TaskDtoView;

/**
 * Component responsible for mapping between Task entities and Task DTOs.
 */
@Component
public class TaskMapper {

    /**
     * Maps a Task entity to a TaskDtoView DTO.
     *
     * @param task The Task entity to map
     * @return The mapped TaskDtoView DTO
     */
    public TaskDtoView map(Task task) {
        return TaskDtoView.builder()
                .id(task.getId())
                .header(task.getHeader())
                .description(task.getDescription())
                .isCompleted(task.isCompleted())
                .dateCompleted(task.getDateCompleted())
                .build();
    }

    /**
     * Maps a TaskDto DTO to a Task entity.
     *
     * @param taskDto The TaskDto DTO to map
     * @return The mapped Task entity
     */
    public Task map(TaskDto taskDto) {
        return Task.builder()
                .header(taskDto.getHeader())
                .description(taskDto.getDescription())
                .build();
    }
}
