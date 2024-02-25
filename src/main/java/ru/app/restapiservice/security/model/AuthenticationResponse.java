package ru.app.restapiservice.security.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Builder
@Getter
public class AuthenticationResponse {
    //f
    private String token;

}
