package ru.app.restapiservice.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    @JsonIgnore
    private String password;

    @Column(name = "date_of_registration", nullable = false)
    private LocalDate dateOfRegistration;

    @Column(name = "first_name", nullable = false, length = 60)
    private String firstName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonIgnore
    private UserRole userRole;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonIgnore
    private List<Task> tasks;

    public void addTask(Task task) {
        this.tasks.add(task);
    }

}
