package ru.app.restapiservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.model.RoleEnum;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.model.UserRole;
import ru.app.restapiservice.repository.RoleRepository;
import ru.app.restapiservice.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void addUser(User user) {

        UserRole userRole = UserRole.builder()
                .owner(user)
                .role(RoleEnum.USER)
                .build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDateOfRegistration(LocalDate.now());
        user.setUserRole(userRole);

        this.userRepository.save(user);
        this.roleRepository.save(userRole);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }
}
