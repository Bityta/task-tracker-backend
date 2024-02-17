package ru.app.restapiservice.model.dto.task;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {

    @Size(min = 5, max = 50, message = "Header must contain from 5 to 50 characters")
    @NotBlank(message = "Header cannot be empty")
    private String header;

    @Size(min = 5, max = 4096, message = "Description must contain from 5 to 4096 characters")
    @NotBlank(message = "Description cannot be empty")
    private String description;

}
