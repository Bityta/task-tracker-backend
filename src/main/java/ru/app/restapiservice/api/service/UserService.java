package ru.app.restapiservice.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.UserRole;
import ru.app.restapiservice.api.repository.RoleRepository;
import ru.app.restapiservice.api.repository.UserRepository;
import ru.app.restapiservice.exception.UserNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Transactional
    public void save(User user, UserRole userRole) {

        this.roleRepository.save(userRole);
        this.userRepository.save(user);

    }

    @Transactional
    public void save(User user) {

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
