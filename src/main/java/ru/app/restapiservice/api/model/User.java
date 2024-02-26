package ru.app.restapiservice.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a user in the application.
 */
@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString(exclude = {"userRole", "tasks"})
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private long id;

    /**
     * The email address of the user.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The date when the user was registered.
     */
    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    /**
     * The first name of the user.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The role of the user.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    private UserRole userRole;

    /**
     * The list of tasks associated with the user.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Task> tasks = new ArrayList<>();

}
