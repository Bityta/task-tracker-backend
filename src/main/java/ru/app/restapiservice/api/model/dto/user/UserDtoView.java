package ru.app.restapiservice.api.model.dto.user;


import lombok.Builder;
import lombok.Data;
import ru.app.restapiservice.api.model.RoleEnum;


@Data
@Builder
public class UserDtoView {

    private long id;

    private String email;

    private String firstName;

    private RoleEnum role;


}
