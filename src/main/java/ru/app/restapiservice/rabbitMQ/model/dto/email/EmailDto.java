package ru.app.restapiservice.rabbitMQ.model.dto.email;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDto {

    private String email;
}
