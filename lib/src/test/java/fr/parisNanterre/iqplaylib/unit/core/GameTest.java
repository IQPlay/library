package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.IGame;
import fr.parisNanterre.iqplaylib.api.IGameSession;
import fr.parisNanterre.iqplaylib.core.Game;
import fr.parisNanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testCreateSession() throws SessionAlreadyExistsException {
        IGame game = new Game("Test Game");
        IGameSession session = game.createSession();

        assertNotNull(session, "La session ne doit pas être nulle");
    }

    @Test
    void testCreateMultipleSessions() throws SessionAlreadyExistsException {
        IGame game = new Game("Test Game");
        game.createSession();

        SessionAlreadyExistsException exception = assertThrows(SessionAlreadyExistsException.class, () -> {
            game.createSession();
        }, "Une exception doit être lancée si une session est déjà en cours");
        assertTrue(exception.getMessage().contains("Une session est déjà en cours"));
    }
}
