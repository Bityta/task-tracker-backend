package ru.app.restapiservice.model.mapper;

import org.springframework.stereotype.Component;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.model.dto.UserDTO;


@Component
public class UserMapper {


    public User map(UserDTO userDTO) {

        return User.builder()
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .password(userDTO.getPassword())
                .build();

    }

    public UserDTO map(User user) {

        return UserDTO.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .password(user.getPassword())
                .build();

    }


}
