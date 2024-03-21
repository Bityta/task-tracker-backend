package ru.app.restapiservice.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.UserRole;
import ru.app.restapiservice.api.repository.RoleRepository;
import ru.app.restapiservice.api.repository.UserRepository;
import ru.app.restapiservice.exception.customException.EmailIsAlreadyUsedException;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.rabbitMQ.model.mapper.EmailMapper;
import ru.app.restapiservice.rabbitMQ.service.RabbitMQService;

import java.util.List;

/**
 * Service for managing users.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RabbitMQService rabbitMQService;
    private final EmailMapper emailMapper;

    /**
     * Saves a new user with the specified role.
     * Sends a greetings message via RabbitMQ.
     *
     * @param user     The user to be saved.
     * @param userRole The role of the user.
     * @throws EmailIsAlreadyUsedException if the email is already used.
     */
    @Transactional
    public void save(User user, UserRole userRole) {
        if (existByEmail(user.getEmail())) {
            throw new EmailIsAlreadyUsedException("This email address is already used");
        }
        rabbitMQService.sendGreetingsMessage(emailMapper.map(user));
        roleRepository.save(userRole);
        userRepository.save(user);
    }

    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user to retrieve.
     * @return The user with the specified email.
     * @throws UserNotFoundException if the user is not found.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with specified email address does not exist"));
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Checks if a user with the specified email exists.
     *
     * @param email The email to check.
     * @return true if a user with the email exists, false otherwise.
     */
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     * @throws UserNotFoundException if the user is not found.
     */
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with specified id does not exist"));
    }
}
