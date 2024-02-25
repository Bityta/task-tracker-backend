package ru.app.restapiservice.security.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    //убрал data
    private String token;

}
