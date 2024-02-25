package ru.app.restapiservice.rabbitMQ.model.dto.email;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailDto {

    private String email;
}
