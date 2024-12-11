package fr.parisnanterre.iqplaylib.unit.core;

import fr.parisnanterre.iqplaylib.Score;
import fr.parisnanterre.iqplaylib.api.*;
import fr.parisnanterre.iqplaylib.core.Game;
import fr.parisnanterre.iqplaylib.core.Level;
import fr.parisnanterre.iqplaylib.core.PlayerAnswer;
import fr.parisnanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionTest {

    private IGameSession gameSession;
    private ILevel level;
    private IScore score;

    @BeforeEach
    void setUp() throws SessionAlreadyExistsException {
        IGame game = new Game("Test Game");
        gameSession = game.createSession();
        level = new Level(1); // Utilisation de la nouvelle classe concrète
        score = new Score();
        gameSession.start(level, score);
    }

    @Test
    void testSessionStart() {
        assertEquals(StateGameSessionEnum.STARTED, gameSession.state(), "La session doit être démarrée");
    }

    @Test
    void testSubmitCorrectAnswer() {
        IQuestion question = gameSession.nextQuestion();
        assertNotNull(question, "La question ne doit pas être nulle");

        IPlayerAnswer playerAnswer = new PlayerAnswer(question.correctAnswer().answer());
        gameSession.submitAnswer(playerAnswer);

        assertEquals(1, gameSession.score().score(), "Le score doit être incrémenté après une bonne réponse");
        assertEquals(2, gameSession.level().level(), "Le niveau doit augmenter après une bonne réponse");
    }

    @Test
    void testSubmitIncorrectAnswer() {
        IQuestion question = gameSession.nextQuestion();
        assertNotNull(question, "La question ne doit pas être nulle");

        IPlayerAnswer playerAnswer = new PlayerAnswer("Mauvaise Réponse");
        gameSession.submitAnswer(playerAnswer);

        assertEquals(StateGameSessionEnum.ENDED, gameSession.state(), "La session doit être terminée après une mauvaise réponse");
    }
}
