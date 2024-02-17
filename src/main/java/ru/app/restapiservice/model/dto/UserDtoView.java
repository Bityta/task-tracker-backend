package ru.app.restapiservice.model.dto;


import lombok.Builder;
import lombok.Data;
import ru.app.restapiservice.model.RoleEnum;


@Data
@Builder
public class UserDtoView {

    private long id;

    private String email;

    private String firstName;

    private RoleEnum role;


}
