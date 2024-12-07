package fr.parisnanterre.iqplaylib.api;

import fr.parisnanterre.iqplaylib.exceptions.SessionAlreadyExistsException;

/**
 * Interface representing a game.
 */
public interface IGame {
    String name();
    IGameSession createSession() throws SessionAlreadyExistsException;
}