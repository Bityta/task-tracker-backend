package ru.app.restapiservice.api.model.mapper.user;

import org.springframework.stereotype.Component;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.dto.user.UserDtoView;
import ru.app.restapiservice.api.model.dto.user.UserLoginDto;
import ru.app.restapiservice.api.model.dto.user.UserRegisterDto;

/**
 * Component responsible for mapping between User entities and User DTOs.
 */
@Component
public class UserMapper {

    /**
     * Maps a User entity to a UserDtoView DTO.
     *
     * @param user The User entity to map
     * @return The mapped UserDtoView DTO
     */
    public UserDtoView map(User user) {
        return UserDtoView.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .role(user.getUserRole().getRole())
                .build();
    }

    /**
     * Maps a UserLoginDto DTO to a User entity.
     *
     * @param userDTO The UserLoginDto DTO to map
     * @return The mapped User entity
     */
    public User map(UserLoginDto userDTO) {
        return User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }

    /**
     * Maps a UserRegisterDto DTO to a User entity.
     *
     * @param user The UserRegisterDto DTO to map
     * @return The mapped User entity
     */
    public User map(UserRegisterDto user) {
        return User.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .password(user.getPassword())
                .build();
    }
}
