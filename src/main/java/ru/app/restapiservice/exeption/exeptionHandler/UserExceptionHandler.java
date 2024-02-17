package ru.app.restapiservice.exeption.exeptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.exeption.customException.EmailIsAlreadyUsedException;
import ru.app.restapiservice.exeption.customException.UserNotFoundException;
import ru.app.restapiservice.model.dto.error.ErrorMessageDtoView;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundExceptions(UserNotFoundException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getError();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleBadCredentialsExceptions(BadCredentialsException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Credentials", ex.getMessage());

        return errors.getError();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailIsAlreadyUsedException.class)
    public Map<String, String> handleEmailIsAlreadyUsedExceptions(EmailIsAlreadyUsedException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getError();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleEmptyBodyExceptions(HttpMessageNotReadableException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Credentials", "Credentials cannot be empty");
        return errors.getError();
    }

}
