package fr.parisNanterre.iqplaylib.core;

import fr.parisNanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.exceptions.SessionAlreadyExistsException;

/**
 * AbstractGame is an abstract class that implements the IGame interface.
 * It provides common functionality for game implementations, including game creation
 * and session management. Concrete subclasses are expected to provide specific
 * implementations for creating game sessions.
 */
public abstract class AbstractGame implements IGame {
    private String name;
    private IGameSession currentSession;

    public AbstractGame(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public IGameSession createSession() throws SessionAlreadyExistsException {
        if (currentSession != null && currentSession.state() != StateGameSessionEnum.ENDED) {
            throw new SessionAlreadyExistsException("Une session est déjà en cours.");
        }
        currentSession = createGameSession();
        return currentSession;
    }

    protected abstract IGameSession createGameSession();
}
