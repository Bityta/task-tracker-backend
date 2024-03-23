package ru.app.restapiservice.api.model.dto.task;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO class representing a view of a task.
 */
@Data
@Builder
public class TaskDtoView {

    /**
     * The unique identifier of the task.
     */
    private long id;

    /**
     * The header of the task.
     */
    private String header;

    /**
     * The description of the task.
     */
    private String description;

    /**
     * Indicates whether the task is completed or not.
     */
    private boolean isCompleted;

    /**
     * The date when the task was completed.
     */
    private LocalDate dateCompleted;
}
