package ru.app.restapiservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.model.Role;
import ru.app.restapiservice.model.RoleEnum;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.repository.RoleRepository;
import ru.app.restapiservice.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    //todo
    //инкодинг пароля
    @Transactional
    public void addUser(User user) {

        Role role = Role.builder()
                .owner(user)
                .role(RoleEnum.USER)
                .build();

        user.setRole(role);


        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2012-05-20T09:00:00.000Z");
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy, Ka").format(date);
            user.setDateOfRegistration(formattedDate);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.userRepository.save(user);
        this.roleRepository.save(role);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }
}
