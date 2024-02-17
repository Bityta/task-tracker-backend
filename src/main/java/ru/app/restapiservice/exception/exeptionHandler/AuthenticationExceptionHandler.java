package ru.app.restapiservice.exception.exeptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.app.restapiservice.exception.customException.EmailIsAlreadyUsedException;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.model.dto.error.ErrorMessageDtoView;

import java.util.Map;

public class AuthenticationExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundExceptions(UserNotFoundException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getError();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailIsAlreadyUsedException.class)
    public Map<String, String> handleEmailIsAlreadyUsedExceptions(EmailIsAlreadyUsedException ex) {

        ErrorMessageDtoView errors = new ErrorMessageDtoView("Email", ex.getMessage());

        return errors.getError();
    }

    //    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(BadCredentialsException.class)
//    public Map<String, String> handleBadCredentialsExceptions(BadCredentialsException ex) {
//
//        ErrorMessageDtoView errors = new ErrorMessageDtoView("Credentials", ex.getMessage());
//
//        return errors.getError();
//    }
//
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public Map<String, String> handleEmptyBodyExceptions(HttpMessageNotReadableException ex) {
//
//        ErrorMessageDtoView errors = new ErrorMessageDtoView("Credentials", "Credentials cannot be empty");
//        return errors.getError();
//    }

}
