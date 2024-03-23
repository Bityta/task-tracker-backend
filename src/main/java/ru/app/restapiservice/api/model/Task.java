package ru.app.restapiservice.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing a task in the application.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tasks")
@EqualsAndHashCode
public class Task {

    /**
     * Unique identifier for the task.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_seq")
    @SequenceGenerator(name = "tasks_id_seq", sequenceName = "tasks_id_seq", allocationSize = 1)
    private long id;

    /**
     * The header or title of the task.
     */
    @Column(name = "header", nullable = false, length = 50)
    private String header;

    /**
     * The description or details of the task.
     */
    @Column(name = "description", length = 4096)
    private String description;

    /**
     * Flag indicating whether the task is completed.
     */
    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    /**
     * The date when the task was completed, if applicable.
     */
    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    /**
     * The owner or creator of the task.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
}
