package ru.app.restapiservice.api.model.dto.user;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserLoginDtoTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidation() {
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("test@example.com")
                .password("password")
                .build();

        assertTrue(validator.validate(userLoginDto).isEmpty(), "Validation should pass for valid object");

        userLoginDto = UserLoginDto.builder()
                .email("invalid-email")
                .password("")
                .build();

        assertFalse(validator.validate(userLoginDto).isEmpty(), "Validation should fail for invalid object");
    }

    @Test
    public void testToString() {
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("test@example.com")
                .password("password")
                .build();

        assertEquals("UserLoginDto(email=test@example.com, password=password)", userLoginDto.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserLoginDto userLoginDto1 = UserLoginDto.builder()
                .email("test@example.com")
                .password("password")
                .build();
        UserLoginDto userLoginDto2 = UserLoginDto.builder()
                .email("test@example.com")
                .password("password")
                .build();

        assertEquals(userLoginDto1, userLoginDto2);
        assertEquals(userLoginDto1.hashCode(), userLoginDto2.hashCode());
    }
}
