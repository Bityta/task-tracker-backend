package ru.app.restapiservice.exeption.customException;

public class EmailIsAlreadyUsedException extends RuntimeException {

    public EmailIsAlreadyUsedException(String message) {
        super(message);
    }
}
