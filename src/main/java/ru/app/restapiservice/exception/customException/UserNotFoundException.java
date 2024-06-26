package ru.app.restapiservice.exception.customException;

/**
 * Custom exception class indicating that a user was not found.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
