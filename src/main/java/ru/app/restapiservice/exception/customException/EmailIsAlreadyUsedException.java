package ru.app.restapiservice.exception.customException;

public class EmailIsAlreadyUsedException extends RuntimeException {
    public EmailIsAlreadyUsedException(String message) {
        super(message);
    }
}
