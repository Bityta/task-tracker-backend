package ru.app.restapiservice.api.model.dto.user;

import org.junit.jupiter.api.Test;
import ru.app.restapiservice.api.model.RoleEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoViewTest {

    @Test
    public void testToString() {
        UserDtoView userDtoView = UserDtoView.builder()
                .id(1)
                .email("test@example.com")
                .firstName("John")
                .role(RoleEnum.USER)
                .build();

        assertEquals("UserDtoView(id=1, email=test@example.com, firstName=John, role=USER)", userDtoView.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserDtoView userDtoView1 = UserDtoView.builder()
                .id(1)
                .email("test@example.com")
                .firstName("John")
                .role(RoleEnum.USER)
                .build();
        UserDtoView userDtoView2 = UserDtoView.builder()
                .id(1)
                .email("test@example.com")
                .firstName("John")
                .role(RoleEnum.USER)
                .build();

        assertEquals(userDtoView1, userDtoView2);
        assertEquals(userDtoView1.hashCode(), userDtoView2.hashCode());
    }
}
