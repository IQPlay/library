package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.Game;
import fr.parisNanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultGameTest {

    @Test
    void testCreateSession() throws SessionAlreadyExistsException {
        IGame game = new Game("Test Game");
        IGameSession session = game.createSession();

        assertNotNull(session, "La session ne doit pas être nulle");
    }

    @Test
    void testCreateMultipleSessions() throws SessionAlreadyExistsException {
        IGame game = new Game("Test Game");
        IGameSession session1 = game.createSession();

        assertThrows(SessionAlreadyExistsException.class, () -> {
            game.createSession();
        }, "Une exception doit être lancée si une session est déjà en cours");
    }
}
