package fr.parisnanterre.iqplaylib.api;

/**
 * Enum representing the various states that a game session can be in.
 *
 * @see IGameSession
 */
public enum StateGameSessionEnum {
    CREATED,
    STARTED,
    PAUSED,
    ENDED,
    INTERRUPTED,
    IN_PROGRESS,
}
