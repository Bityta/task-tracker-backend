package ru.app.restapiservice.exception.exeptionHandler;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.controller.UserController;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.model.dto.error.ErrorMessageDtoView;

import java.util.Map;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    @Hidden
    public Map<String, String> handleUserNotFoundExceptions(UserNotFoundException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getError();
    }
}
