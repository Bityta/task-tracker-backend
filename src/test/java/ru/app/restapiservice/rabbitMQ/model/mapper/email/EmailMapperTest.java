package ru.app.restapiservice.rabbitMQ.model.mapper.email;

import org.junit.jupiter.api.Test;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.rabbitMQ.model.dto.EmailGreetingsDto;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmailMapperTest {

    @Test
    void testMap() {
        User user = new User();
        user.setEmail("test@example.com");
        EmailMapper emailMapper = new EmailMapper();
        EmailGreetingsDto emailDto = emailMapper.map(user);
        assertEquals(user.getEmail(), emailDto.getEmail());
    }

}
