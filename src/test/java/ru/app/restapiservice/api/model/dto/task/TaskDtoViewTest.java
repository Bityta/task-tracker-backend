package ru.app.restapiservice.api.model.dto.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskDtoViewTest {

    @Test
    public void testToString() {
        TaskDtoView taskDtoView = TaskDtoView.builder()
                .id(1)
                .header("Header")
                .description("Description")
                .isCompleted(false)
                .dateCompleted(LocalDate.now())
                .build();

        assertEquals("TaskDtoView(id=1, header=Header, description=Description, isCompleted=false, dateCompleted=" + LocalDate.now() + ")", taskDtoView.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        TaskDtoView taskDtoView1 = TaskDtoView.builder()
                .id(1)
                .header("Header")
                .description("Description")
                .isCompleted(false)
                .dateCompleted(LocalDate.now())
                .build();
        TaskDtoView taskDtoView2 = TaskDtoView.builder()
                .id(1)
                .header("Header")
                .description("Description")
                .isCompleted(false)
                .dateCompleted(LocalDate.now())
                .build();

        assertEquals(taskDtoView1, taskDtoView2);
        assertEquals(taskDtoView1.hashCode(), taskDtoView2.hashCode());
    }
}
