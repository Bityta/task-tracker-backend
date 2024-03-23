package ru.app.restapiservice.api.model.mapper.user;

import org.junit.jupiter.api.Test;
import ru.app.restapiservice.api.model.RoleEnum;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.UserRole;
import ru.app.restapiservice.api.model.dto.user.UserDtoView;
import ru.app.restapiservice.api.model.dto.user.UserLoginDto;
import ru.app.restapiservice.api.model.dto.user.UserRegisterDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    @Test
    public void testMapToUserDtoView() {
        User user = User.builder()
                .id(1)
                .email("test@example.com")
                .firstName("John")
                .userRole(UserRole.builder().role(RoleEnum.USER).build())
                .build();

        UserMapper userMapper = new UserMapper();
        UserDtoView userDtoView = userMapper.map(user);

        assertEquals(user.getId(), userDtoView.getId());
        assertEquals(user.getEmail(), userDtoView.getEmail());
        assertEquals(user.getFirstName(), userDtoView.getFirstName());
        assertEquals(user.getUserRole().getRole(), userDtoView.getRole());
    }

    @Test
    public void testMapToUserFromUserLoginDto() {
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("test@example.com")
                .password("password")
                .build();

        UserMapper userMapper = new UserMapper();
        User user = userMapper.map(userLoginDto);

        assertEquals(userLoginDto.getEmail(), user.getEmail());
        assertEquals(userLoginDto.getPassword(), user.getPassword());
    }

    @Test
    public void testMapToUserFromUserRegisterDto() {
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .email("test@example.com")
                .firstName("John")
                .password("password")
                .build();

        UserMapper userMapper = new UserMapper();
        User user = userMapper.map(userRegisterDto);

        assertEquals(userRegisterDto.getEmail(), user.getEmail());
        assertEquals(userRegisterDto.getFirstName(), user.getFirstName());
        assertEquals(userRegisterDto.getPassword(), user.getPassword());
    }
}
