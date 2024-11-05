package fr.parisNanterre.iqplaylib.integration;

import fr.parisNanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.*;
import fr.parisNanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameIntegrationTest {

    @Test
    void testCompleteGameFlow() throws SessionAlreadyExistsException {
        IGame game = new Game("Test Game");
        IGameSession session = game.createSession();
        session.start();

        int maxIterations = 100; // Limiter à 100 questions pour éviter une boucle infinie
        int iterations = 0;

        // Jouer jusqu'à ce que la session se termine ou que le nombre maximal d'itérations soit atteint
        while (session.state() == StateGameSessionEnum.STARTED && iterations < maxIterations) {
            IQuestion question = session.nextQuestion();
            IPlayerAnswer playerAnswer;

            // Simuler une réponse correcte
            playerAnswer = new PlayerAnswer(question.correctAnswer().answer());
            session.submitAnswer(playerAnswer);

            iterations++;
        }

        // Si la session est toujours en cours après le maximum d'itérations, on la termine
        if (session.state() == StateGameSessionEnum.STARTED) {
            session.end();
        }

        // Vérifier que le score est positif
        assertTrue(session.score().score() > 0, "Le score doit être positif");

        // Vérifier que la session est terminée
        assertEquals(StateGameSessionEnum.ENDED, session.state(), "La session doit être terminée");
    }
}
