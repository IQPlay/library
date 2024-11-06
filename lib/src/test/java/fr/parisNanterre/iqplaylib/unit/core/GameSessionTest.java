package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.Game;
import fr.parisNanterre.iqplaylib.core.PlayerAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionTest {

    private IGameSession gameSession;

    @BeforeEach
    void setUp() {
        IGame game = new Game("Test Game");
        gameSession = game.createSession();
    }

    @Test
    void testSessionStart() {
        gameSession.start();
        assertEquals(StateGameSessionEnum.STARTED, gameSession.state(), "La session doit être démarrée");
    }

    @Test
    void testNextQuestion() {
        gameSession.start();
        IQuestion question = gameSession.nextQuestion();
        assertNotNull(question, "La question ne doit pas être nulle");
    }

    @Test
    void testSubmitCorrectAnswer() {
        gameSession.start();
        IQuestion question = gameSession.nextQuestion();
        IPlayerAnswer playerAnswer = new PlayerAnswer(question.correctAnswer().answer());
        gameSession.submitAnswer(playerAnswer);

        assertEquals(1, gameSession.score().score(), "Le score doit être incrémenté");
        assertEquals(2, gameSession.level().level(), "Le niveau doit augmenter");
    }

    @Test
    void testSubmitIncorrectAnswer() {
        gameSession.start();
        gameSession.nextQuestion();
        IPlayerAnswer playerAnswer = new PlayerAnswer("Mauvaise Réponse");
        gameSession.submitAnswer(playerAnswer);

        assertEquals(StateGameSessionEnum.ENDED, gameSession.state(), "La session doit être terminée");
    }
}
