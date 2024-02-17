package ru.app.restapiservice.model.dto.task;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskDtoView {

    private long id;

    private String header;

    private String description;

    private boolean isCompleted;

    private Date dateCompleted;
}
