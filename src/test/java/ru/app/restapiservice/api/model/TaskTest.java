package ru.app.restapiservice.api.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {

    @Test
    public void testConstructorAndGetters() {
        Task task = new Task();
        task.setId(1);
        task.setHeader("Test task");
        task.setDescription("Description of test task");
        task.setCompleted(false);
        task.setDateCompleted(LocalDate.now());
        User owner = new User();
        task.setOwner(owner);

        assertEquals(1, task.getId());
        assertEquals("Test task", task.getHeader());
        assertEquals("Description of test task", task.getDescription());
        assertFalse(task.isCompleted());
        assertEquals(LocalDate.now(), task.getDateCompleted());
        assertEquals(owner, task.getOwner());
    }

    @Test
    public void testToString() {
        Task task = new Task();
        task.setId(1);
        task.setHeader("Test task");
        task.setDescription("Description of test task");
        task.setCompleted(false);
        task.setDateCompleted(LocalDate.now());
        User owner = new User();
        task.setOwner(owner);

        assertEquals("Task(id=1, header=Test task, description=Description of test task, isCompleted=false, dateCompleted=" + LocalDate.now() + ", owner=" + owner + ")", task.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Task task1 = new Task(1, "Test task", "Description of test task", false, LocalDate.now(), new User());
        Task task2 = new Task(1, "Test task", "Description of test task", false, LocalDate.now(), new User());

        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void testBuilder() {
        Task task = Task.builder()
                .id(1)
                .header("Test task")
                .description("Description of test task")
                .dateCompleted(LocalDate.now())
                .owner(new User())
                .build();

        assertEquals(1, task.getId());
        assertEquals("Test task", task.getHeader());
        assertEquals("Description of test task", task.getDescription());
        assertFalse(task.isCompleted());
        assertEquals(LocalDate.now(), task.getDateCompleted());
    }
}