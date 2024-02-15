package ru.app.restapiservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@ToString
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_seq")
    @SequenceGenerator(name = "tasks_id_seq", sequenceName = "tasks_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "header", nullable = false, length = 50)
    private String header;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    @Column(name = "date_completed")
    private Date dateCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User owner;


}
