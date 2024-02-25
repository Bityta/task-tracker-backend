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
import ru.app.restapiservice.rabbitMQ.model.mapper.email.EmailMapper;
import ru.app.restapiservice.rabbitMQ.service.RabbitMQService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RabbitMQService rabbitMQService;
    private final EmailMapper emailMapper;

    @Transactional
    public void save(User user, UserRole userRole) {

        if (this.existByEmail(user.getEmail())) {
            throw new EmailIsAlreadyUsedException("This email address is already used");
        }
        this.rabbitMQService.sendGreetingsMessage(
                this.emailMapper.map(user)
        );
        this.roleRepository.save(userRole);
        this.userRepository.save(user);

    }


    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with specified email address does not exist"));
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public boolean existByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User findById(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with specified id does not exist"));
    }
}
