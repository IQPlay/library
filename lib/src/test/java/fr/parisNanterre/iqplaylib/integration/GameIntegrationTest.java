package fr.parisnanterre.iqplaylib.integration;

import fr.parisnanterre.iqplaylib.Level;
import fr.parisnanterre.iqplaylib.Score;
import fr.parisnanterre.iqplaylib.api.*;
import fr.parisnanterre.iqplaylib.core.Game;
import fr.parisnanterre.iqplaylib.core.PlayerAnswer;
import fr.parisnanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameIntegrationTest {

    @Test
    void testCompleteGameFlow() throws SessionAlreadyExistsException {
        IGame game = new Game("Test Game");
        IGameSession session = game.createSession();
        ILevel level = new Level(1);
        IScore score = new Score(1);
        session.start(level, score);

        int maxIterations = 10;
        int iterations = 0;

        while (session.state() == StateGameSessionEnum.STARTED && iterations < maxIterations) {
            IQuestion question = session.nextQuestion();
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
