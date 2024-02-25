package ru.app.restapiservice.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonIgnore
    private UserRole userRole;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
//    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();
    ;


}
