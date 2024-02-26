package ru.app.restapiservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.restapiservice.api.model.UserRole;

/**
 * Repository interface for managing user roles.
 * Provides CRUD operations for UserRole entities in the database.
 */
public interface RoleRepository extends JpaRepository<UserRole, Long> {
}
