package ru.app.restapiservice.exeption.exeptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.exeption.customException.EmailIsAlreadyUsedException;
import ru.app.restapiservice.exeption.customException.UserNotFoundException;
import ru.app.restapiservice.model.dto.ErrorMessageDtoView;

import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.setFieldName(fieldName);
            errors.setErrorMessage(errorMessage);
        });

        return errors.getMap();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundExceptions(UserNotFoundException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getMap();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleBadCredentialsExceptions(BadCredentialsException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Credentials", ex.getMessage());

        return errors.getMap();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailIsAlreadyUsedException.class)
    public Map<String, String> handleEmailIsAlreadyUsedExceptions(EmailIsAlreadyUsedException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getMap();
    }

}
