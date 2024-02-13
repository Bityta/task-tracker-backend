package ru.app.restapiservice.model;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "date_of_registration", nullable = false)
    private LocalDate dateOfRegistration;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private UserRole userRole;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Task> tasks;

}
