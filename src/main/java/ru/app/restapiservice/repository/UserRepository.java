package ru.app.restapiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.restapiservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
