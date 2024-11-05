package fr.parisNanterre.iqplaylib.exceptions;

/**
 * Exception thrown to indicate that a session already exists and cannot be created again.
 * This exception typically occurs when trying to create a new game session while an existing session
 * is still active or has not been properly terminated.
 *
 * @see fr.parisNanterre.iqplaylib.api.IGameSession
 */
public class SessionAlreadyExistsException extends RuntimeException {
    public SessionAlreadyExistsException(String message) {
        super(message);
    }
}
