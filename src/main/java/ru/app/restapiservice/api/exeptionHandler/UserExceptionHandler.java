package ru.app.restapiservice.api.exeptionHandler;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.api.controller.UserController;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.exception.model.dto.ErrorMessageDtoView;

import java.util.Map;

@RestControllerAdvice(assignableTypes = UserController.class)
@Hidden
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {

        ErrorMessageDtoView errors = ErrorMessageDtoView.builder()
                .status(HttpStatus.CONFLICT)
                .error(ex.getMessage())
                .path("/user/{id}")
                .build();

        return errors.getError();
    }
}
