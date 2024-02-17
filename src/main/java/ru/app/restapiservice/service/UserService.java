package ru.app.restapiservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.exeption.customException.UserNotFoundException;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.model.UserRole;
import ru.app.restapiservice.repository.RoleRepository;
import ru.app.restapiservice.repository.UserRepository;

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
