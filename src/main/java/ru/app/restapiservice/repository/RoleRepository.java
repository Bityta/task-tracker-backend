package ru.app.restapiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.restapiservice.model.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
}
