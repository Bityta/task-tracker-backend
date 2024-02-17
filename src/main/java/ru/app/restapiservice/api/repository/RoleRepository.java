package ru.app.restapiservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.restapiservice.api.model.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
}
