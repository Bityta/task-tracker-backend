package ru.app.restapiservice.api.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testBuilderAndGetters() {
        User user = User.builder()
                .id(1)
                .email("test@example.com")
                .password("password")
                .dateOfRegistration(LocalDate.now())
                .firstName("John")
                .build();

        assertEquals(1, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(LocalDate.now(), user.getDateOfRegistration());
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testConstructorAndGetters() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setDateOfRegistration(LocalDate.now());
        user.setFirstName("John");

        assertEquals(1, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(LocalDate.now(), user.getDateOfRegistration());
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testToString() {
        User user = User.builder()
                .id(1)
                .email("test@example.com")
                .password("password")
                .dateOfRegistration(LocalDate.now())
                .firstName("John")
                .build();

        assertEquals("User(id=1, email=test@example.com, password=password, dateOfRegistration=" + LocalDate.now() + ", firstName=John)", user.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        List<Task> tasks = List.of(
                new Task(),
                new Task()
        );
        User user1 = User.builder()
                .id(1)
                .email("test@example.com")
                .password("password")
                .dateOfRegistration(LocalDate.now())
                .firstName("John")
                .tasks(tasks)
                .build();

        User user2 = User.builder()
                .id(1)
                .email("test@example.com")
                .password("password")
                .dateOfRegistration(LocalDate.now())
                .firstName("John")
                .tasks(tasks)
                .build();

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
