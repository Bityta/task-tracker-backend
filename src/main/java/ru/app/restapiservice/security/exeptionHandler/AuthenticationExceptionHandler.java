package ru.app.restapiservice.security.exeptionHandler;

import feign.FeignException;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.app.restapiservice.exception.customException.EmailIsAlreadyUsedException;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.exception.model.dto.ErrorMessageDtoView;
import ru.app.restapiservice.security.controller.AuthenticationController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Exception handler for user authentication related exceptions.
 */
@RestControllerAdvice(assignableTypes = AuthenticationController.class)
@Hidden()
public class AuthenticationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationExceptionHandler.class);

    /**
     * Exception handler for EmailIsAlreadyUsedException.
     *
     * @param ex The EmailIsAlreadyUsedException instance.
     * @return A map containing error details.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailIsAlreadyUsedException.class)
    public Map<String, String> handleEmailIsAlreadyUsedException(EmailIsAlreadyUsedException ex) {
        LOGGER.error("User registration error. {}", ex.getMessage());
        ErrorMessageDtoView errors = ErrorMessageDtoView.builder()
                .status(HttpStatus.CONFLICT)
                .error(ex.getMessage())
                .path("/reg")
                .build();
        return errors.getError();
    }

    /**
     * Exception handler for BadCredentialsException.
     *
     * @param ex The BadCredentialsException instance.
     * @return A map containing error details.
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleBadCredentialsException(BadCredentialsException ex) {
        LOGGER.error("User data invalid. {}", ex.getMessage());
        ErrorMessageDtoView errors = ErrorMessageDtoView.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .error(ex.getMessage())
                .path("/log")
                .build();
        return errors.getError();
    }

    /**
     * Exception handler for UserNotFoundException.
     *
     * @param ex The UserNotFoundException instance.
     * @return A map containing error details.
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
        LOGGER.error("User data invalid. {}", ex.getMessage());
        ErrorMessageDtoView errors = ErrorMessageDtoView.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .error(ex.getMessage())
                .path("/log")
                .build();
        return errors.getError();
    }

    /**
     * Exception handler for MethodArgumentNotValidException.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @return A map containing error details.
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
                .path("/reg || /log")
                .build();
        return error.getError();
    }

    /**
     * Exception handler for FeignException.
     *
     * @param ex The FeignException instance.
     * @return A map containing error details.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FeignException.class)
    public Map<String, String> handleFeignException(FeignException ex) {
        LOGGER.error("Error microservice. {}", ex.getMessage());
        ErrorMessageDtoView errors = ErrorMessageDtoView.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(ex.getMessage())
                .path("/reg || /log")
                .build();
        return errors.getError();
    }

}
