package ru.app.restapiservice.rabbitMQ.model.mapper;

import org.springframework.stereotype.Component;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.rabbitMQ.model.dto.EmailGreetingsDto;

@Component
public class EmailMapper {

    public EmailGreetingsDto map(User user) {
        return EmailGreetingsDto.builder()
                .email(user.getEmail())
                .build();
    }
}
