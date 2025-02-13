package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * Represents a specific implementation of a game.
 * Extends the AbstractGame class to provide functionality for creating game sessions.
 */
public class Game extends AbstractGame {

    public Game(String name) {
        super(name);
    }

    @Override
    protected IGameSession createGameSession() {
        return new GameSession(this);
    }
}
