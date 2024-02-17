package ru.app.restapiservice.security.exeptionHandler;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.api.model.dto.error.ErrorMessageDtoView;
import ru.app.restapiservice.exception.EmailIsAlreadyUsedException;
import ru.app.restapiservice.security.config.AuthenticationController;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(assignableTypes = AuthenticationController.class)
@Hidden()
public class AuthenticationExceptionHandler {


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailIsAlreadyUsedException.class)
    public Map<String, String> handleEmailIsAlreadyUsedExceptions(EmailIsAlreadyUsedException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getError();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleBadCredentialsExceptions(BadCredentialsException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Credentials", ex.getMessage());

        return errors.getError();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Hidden()
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
