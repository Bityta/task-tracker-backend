package ru.app.restapiservice.rabbitMQ.model.dto.email;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * DTO class for email information.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Email", description = "Information about email")
public class EmailDto {
    /**
     * The email address.
     */
    private String email;
}
