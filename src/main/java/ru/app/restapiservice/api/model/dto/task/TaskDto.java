package ru.app.restapiservice.api.model.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * DTO class representing information about a task.
 */
@Data
@Builder
@Schema(name = "Task", description = "Information about task")
public class TaskDto {

    /**
     * The header of the task.
     */
    @Size(min = 5, max = 50, message = "Header must contain from 5 to 50 characters")
    @NotBlank(message = "Header cannot be empty")
    private String header;

    /**
     * The description of the task.
     */
    @Size(min = 5, max = 4096, message = "Description must contain from 5 to 4096 characters")
    @NotBlank(message = "Description cannot be empty")
    private String description;
}
