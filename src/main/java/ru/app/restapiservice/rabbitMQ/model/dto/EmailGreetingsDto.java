package ru.app.restapiservice.rabbitMQ.model.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) for representing an email.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailGreetingsDto {
    /**
     * The email address.
     */

    private String email;
}
