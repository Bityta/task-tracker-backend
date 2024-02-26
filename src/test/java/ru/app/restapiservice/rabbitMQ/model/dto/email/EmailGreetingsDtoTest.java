package ru.app.restapiservice.rabbitMQ.model.dto.email;

import org.junit.jupiter.api.Test;
import ru.app.restapiservice.rabbitMQ.model.dto.EmailGreetingsDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailGreetingsDtoTest {

    @Test
    public void testConstructorAndGetters() {
        String email = "test@example.com";
        EmailGreetingsDto emailGreetingsDto = new EmailGreetingsDto(email);
        assertEquals(email, emailGreetingsDto.getEmail());
    }

    @Test
    public void testSetter() {
        String email = "test@example.com";
        EmailGreetingsDto emailGreetingsDto = EmailGreetingsDto.builder()
                .email(email)
                .build();
        emailGreetingsDto.setEmail(email);
        assertEquals(email, emailGreetingsDto.getEmail());
    }

    @Test
    public void testGetEmail() {
        // Given
        String email = "test@example.com";
        EmailGreetingsDto dto = new EmailGreetingsDto(email);

        // When
        String retrievedEmail = dto.getEmail();

        // Then
        assertEquals(email, retrievedEmail);
    }

    @Test
    public void testSetEmail() {
        // Given
        String email = "test@example.com";
        EmailGreetingsDto dto = new EmailGreetingsDto();

        // When
        dto.setEmail(email);

        // Then
        assertEquals(email, dto.getEmail());
    }
}
