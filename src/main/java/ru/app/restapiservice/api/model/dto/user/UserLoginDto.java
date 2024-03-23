package ru.app.restapiservice.api.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(name = "User Login", description = "Information about logged in users")
public class UserLoginDto {

    @Size(min = 5, max = 50, message = "Email must contain from 5 to 50 characters")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;


    @Size(min = 5, max = 50, message = "Password must contain from 5 to 50 characters")
    @NotBlank(message = "Password cannot be empty")
    @Transient
    private String password;

}
