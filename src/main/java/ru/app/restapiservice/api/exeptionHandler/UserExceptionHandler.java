package ru.app.restapiservice.api.exeptionHandler;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.api.controller.UserController;
import ru.app.restapiservice.api.model.dto.error.ErrorMessageDtoView;
import ru.app.restapiservice.customException.customException.UserNotFoundException;

import java.util.Map;

@RestControllerAdvice(assignableTypes = UserController.class)
@Hidden
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundExceptions(UserNotFoundException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getError();
    }
}
