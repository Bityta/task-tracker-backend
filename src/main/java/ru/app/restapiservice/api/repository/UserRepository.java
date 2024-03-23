package ru.app.restapiservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.restapiservice.api.model.User;

import java.util.Optional;

/**
 * Repository interface for managing users.
 * Provides CRUD operations for User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find
     * @return an Optional containing the user with the specified email, if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user with the specified email address exists.
     *
     * @param email the email address to check
     * @return true if a user with the email address exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the user with the specified ID, if found
     */
    Optional<User> findById(long id);
}
