package ru.app.restapiservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Schema(description = "Запрос на аутентификацию")


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {


    @Size(min = 5, max = 50, message = "Email must contain from 5 to 50 characters")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;


    @Size(min = 5, max = 50, message = "Password must contain from 5 to 50 characters")
    @NotBlank(message = "Password cannot be empty")
    @JsonIgnore
    private String password;


    @Size(min = 5, max = 50, message = "First name must contain from 5 to 50 characters")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;


//    @Schema(description = "Имя пользователя", example = "Jon") - swagger

}
