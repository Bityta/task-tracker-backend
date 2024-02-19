package ru.app.restapiservice.api.exeptionHandler;


import io.swagger.v3.oas.annotations.Hidden;
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

@RestControllerAdvice(assignableTypes = TaskController.class)
@Hidden()
public class TaskExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

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
                .path("/")
                .build();

        return error.getError();
    }


}
