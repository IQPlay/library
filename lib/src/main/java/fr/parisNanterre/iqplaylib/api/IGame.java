package fr.parisNanterre.iqplaylib.api;

import fr.parisNanterre.iqplaylib.exceptions.SessionAlreadyExistsException;

/**
 * Interface representing a game.
 */
public interface IGame {
    String name();
    IGameSession createSession() throws SessionAlreadyExistsException;
}