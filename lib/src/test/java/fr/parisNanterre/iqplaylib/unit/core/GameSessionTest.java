package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.Game;
import fr.parisNanterre.iqplaylib.core.DefaultPlayerAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultGameSessionTest {

    private IGameSession gameSession;

    @BeforeEach
    void setUp() {
        IGame game = new Game("Test Game");
        gameSession = game.createSession();
    }

    @Test
    void testSessionStart() {
        gameSession.start();
        assertEquals(StateGameSessionEnum.STARTED, gameSession.getState(), "La session doit être démarrée");
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
        IPlayerAnswer playerAnswer = new DefaultPlayerAnswer(question.getCorrectAnswer().getAnswerText());
        gameSession.submitAnswer(playerAnswer);

        assertEquals(1, gameSession.getScore().getScoreValue(), "Le score doit être incrémenté");
        assertEquals(2, gameSession.getLevel().getLevel(), "Le niveau doit augmenter");
    }

    @Test
    void testSubmitIncorrectAnswer() {
        gameSession.start();
        gameSession.nextQuestion();
        IPlayerAnswer playerAnswer = new DefaultPlayerAnswer("Mauvaise Réponse");
        gameSession.submitAnswer(playerAnswer);

        assertEquals(StateGameSessionEnum.ENDED, gameSession.getState(), "La session doit être terminée");
    }
}
