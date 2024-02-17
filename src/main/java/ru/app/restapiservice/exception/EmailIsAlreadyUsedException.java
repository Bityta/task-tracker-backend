package ru.app.restapiservice.exception;

public class EmailIsAlreadyUsedException extends RuntimeException {

    public EmailIsAlreadyUsedException(String message) {
        super(message);
    }
}
