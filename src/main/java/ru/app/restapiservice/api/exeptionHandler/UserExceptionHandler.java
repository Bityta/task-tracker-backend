package ru.app.restapiservice.api.exeptionHandler;

import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.api.controller.UserController;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.exception.model.dto.ErrorMessageDtoView;

import java.util.Map;

/**
 * Global exception handler for UserController.
 * This class handles exceptions thrown by UserController and provides appropriate error responses.
 */
@RestControllerAdvice(assignableTypes = UserController.class)
@Hidden
public class UserExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

    /**
     * Handles UserNotFoundException and returns a response with HTTP status code 409 Conflict.
     *
     * @param ex The UserNotFoundException instance.
     * @return A map containing error information.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
        LOGGER.error("User not found. {}", ex.getMessage());
        ErrorMessageDtoView errors = ErrorMessageDtoView.builder()
                .status(HttpStatus.CONFLICT)
                .error(ex.getMessage())
                .path("/user/{id}")
                .build();

        return errors.getError();
    }
}
