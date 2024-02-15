package ru.app.restapiservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
@ToString
public class UserRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_roles_id_seq")
    @SequenceGenerator(name = "users_roles_id_seq", sequenceName = "users_roles_id_seq", allocationSize = 1)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private RoleEnum role;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User owner;
}
