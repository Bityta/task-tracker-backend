package ru.app.restapiservice.exception.customException;

/**
 * Custom exception class indicating that the provided email address is already in use.
 */
public class EmailIsAlreadyUsedException extends RuntimeException {

    /**
     * Constructs a new EmailIsAlreadyUsedException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public EmailIsAlreadyUsedException(String message) {
        super(message);
    }
}
