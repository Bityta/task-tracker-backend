package ru.app.restapiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.restapiservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
