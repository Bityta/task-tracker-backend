package ru.app.restapiservice.api.model.mapper;

import org.springframework.stereotype.Component;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.dto.user.UserDtoView;
import ru.app.restapiservice.api.model.dto.user.UserLoginDto;
import ru.app.restapiservice.api.model.dto.user.UserRegisterDto;


@Component
public class UserMapper {

    public UserDtoView map(User user) {
        return UserDtoView.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .role(user.getUserRole().getRole())
                .build();

    }

    public User map(UserLoginDto userDTO) {
        return User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

    }

    public User map(UserRegisterDto user) {
        return User.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .password(user.getPassword())
                .build();

    }

}
