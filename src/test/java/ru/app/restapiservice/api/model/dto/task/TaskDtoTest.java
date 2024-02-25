package ru.app.restapiservice.api.model.dto.task;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskDtoTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidation() {
        TaskDto taskDto = TaskDto.builder()
                .header("Header")
                .description("Description")
                .build();

        assertTrue(validator.validate(taskDto).isEmpty(), "Validation should pass for valid object");

        taskDto = TaskDto.builder()
                .header("H")
                .description("")
                .build();

        assertFalse(validator.validate(taskDto).isEmpty(), "Validation should fail for invalid object");
    }

    @Test
    public void testToString() {
        TaskDto taskDto = TaskDto.builder()
                .header("Header")
                .description("Description")
                .build();

        assertEquals("TaskDto(header=Header, description=Description)", taskDto.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        TaskDto taskDto1 = TaskDto.builder()
                .header("Header")
                .description("Description")
                .build();
        TaskDto taskDto2 = TaskDto.builder()
                .header("Header")
                .description("Description")
                .build();

        assertEquals(taskDto1, taskDto2);
        assertEquals(taskDto1.hashCode(), taskDto2.hashCode());
    }
}
