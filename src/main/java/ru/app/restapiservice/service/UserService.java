package ru.app.restapiservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.model.UserRole;
import ru.app.restapiservice.repository.RoleRepository;
import ru.app.restapiservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;


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

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }


    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public boolean existByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
