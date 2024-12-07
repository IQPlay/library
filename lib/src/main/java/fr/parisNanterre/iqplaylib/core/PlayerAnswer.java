package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The `PlayerAnswer` class represents an answer provided by a player within a game session.
 * This class implements the `IPlayerAnswer` interface, which extends the `IAnswer` interface,
 * indicating that the answer originates from a player participating in the game.
 *
 * Instances of `PlayerAnswer` store the text of the player's response, which can be retrieved
 * through the `answer()` method.
 */
public class PlayerAnswer implements IPlayerAnswer {
    private String answerText;

    public PlayerAnswer(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String answer() {
        return answerText;
    }
}
