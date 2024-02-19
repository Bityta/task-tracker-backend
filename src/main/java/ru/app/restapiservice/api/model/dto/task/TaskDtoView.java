package ru.app.restapiservice.api.model.dto.task;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDtoView {

    private long id;

    private String header;

    private String description;

    private boolean isCompleted;

    private LocalDate dateCompleted;
}
