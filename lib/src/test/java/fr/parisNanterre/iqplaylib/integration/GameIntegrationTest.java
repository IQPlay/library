package fr.parisNanterre.iqplaylib.integration;

import fr.parisNanterre.iqplaylib.Level;
import fr.parisNanterre.iqplaylib.Score;
import fr.parisnanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.Game;
import fr.parisNanterre.iqplaylib.core.PlayerAnswer;
import fr.parisNanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameIntegrationTest {

    private IGame game;
    private IGameSession session;
    private ILevel level;
    private IScore score;

    @BeforeEach
    void setUp() throws SessionAlreadyExistsException {
        game = new Game("Test Game");
        session = game.createSession();
        level = new Level(); // Initialisation du niveau
        score = new Score(); // Initialisation du score
        session.start(level, score);
    }

    @Test
    void testCompleteGameFlow() throws SessionAlreadyExistsException {
        int maxIterations = 10;
        int iterations = 0;

        while (session.state() == StateGameSessionEnum.STARTED && iterations < maxIterations) {
            IQuestion question = session.nextQuestion();
            assertNotNull(question, "La question générée ne doit pas être nulle");
            IPlayerAnswer playerAnswer = new PlayerAnswer(question.correctAnswer().answer());
            session.submitAnswer(playerAnswer);
            iterations++;
        }

        if (session.state() == StateGameSessionEnum.STARTED) {
            session.end();
        }

        assertTrue(session.score().score() >= 0, "Le score doit être positif ou nul");
        assertEquals(StateGameSessionEnum.ENDED, session.state(), "La session doit être terminée");
    }
}
