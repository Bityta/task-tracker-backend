package ru.app.restapiservice.rabbitMQ.model.dto.email;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailDtoTest {

    @Test
    void testBuilder() {
        String email = "test@example.com";
        EmailDto emailDto = EmailDto.builder()
                .email(email)
                .build();

        assertEquals(email, emailDto.getEmail());
    }

}