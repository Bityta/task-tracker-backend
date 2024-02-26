package ru.app.restapiservice.api.exeptionHandler;

import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.api.controller.TaskController;
import ru.app.restapiservice.exception.model.dto.ErrorMessageDtoView;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for TaskController.
 * This class handles exceptions thrown by TaskController and provides appropriate error responses.
 */
@RestControllerAdvice(assignableTypes = TaskController.class)
@Hidden
public class TaskExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskExceptionHandler.class);

    /**
     * Handles MethodArgumentNotValidException and returns a response with HTTP status code 400 Bad Request.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @return A map containing error information.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        LOGGER.error("User data validation error. {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorMessageDtoView error = ErrorMessageDtoView.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error(errors.keySet().stream()
                        .map(errors::get)
                        .collect(Collectors.joining(". ")))
                .path("/tasks")
                .build();

        return error.getError();
    }
}
