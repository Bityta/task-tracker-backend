package ru.app.restapiservice.api.model.dto.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegisterDtoTest {

    @Test
    public void testToString() {
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .email("test@example.com")
                .password("password")
                .firstName("John")
                .build();

        assertEquals("UserRegisterDto(email=test@example.com, password=password, firstName=John)", userRegisterDto.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserRegisterDto userRegisterDto1 = UserRegisterDto.builder()
                .email("test@example.com")
                .password("password")
                .firstName("John")
                .build();
        UserRegisterDto userRegisterDto2 = UserRegisterDto.builder()
                .email("test@example.com")
                .password("password")
                .firstName("John")
                .build();

        assertEquals(userRegisterDto1, userRegisterDto2);
        assertEquals(userRegisterDto1.hashCode(), userRegisterDto2.hashCode());
    }
}
