package fr.parisnanterre.iqplaylib.unit.core;

import fr.parisnanterre.iqplaylib.Level;
import fr.parisnanterre.iqplaylib.Score;
import fr.parisnanterre.iqplaylib.api.*;
import fr.parisnanterre.iqplaylib.core.Game;
import fr.parisnanterre.iqplaylib.core.PlayerAnswer;
import fr.parisnanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionTest {

    private IGameSession gameSession;

    @BeforeEach
    void setUp() throws SessionAlreadyExistsException {
        IGame game = new Game("Test Game");
        gameSession = game.createSession();
    }

    @Test
    void testSessionStart() {
        ILevel level = new Level(1);
        IScore score = new Score(1);
        gameSession.start(level, score);
        assertEquals(StateGameSessionEnum.STARTED, gameSession.state(), "La session doit être démarrée");
    }

    @Test
    void testNextQuestion() {
        ILevel level = new Level(1);
        IScore score = new Score(1);
        gameSession.start(level, score);
        IQuestion question = gameSession.nextQuestion();
        assertNotNull(question, "La question ne doit pas être nulle");
    }

    @Test
    void testNextQuestionBeforeStart() {
        // Selon le comportement attendu, ceci peut soit renvoyer une question soit nécessiter un test d'exception.
        // Ici, on s'attend à ce que la question soit générée même avant start, mais si ce n'est pas logique,
        // ajouter une assertion différente.
        IQuestion question = gameSession.nextQuestion();
        assertNotNull(question, "Même avant start, une question est générée. Vérifier la logique si besoin.");
    }

    @Test
    void testSubmitCorrectAnswer() {
        ILevel level = new Level(1);
        IScore score = new Score(1);
        gameSession.start(level, score);
        IQuestion question = gameSession.nextQuestion();
        IPlayerAnswer playerAnswer = new PlayerAnswer(question.correctAnswer().answer());
        gameSession.submitAnswer(playerAnswer);

        assertEquals(1, gameSession.score().score(), "Le score doit être incrémenté");
        assertEquals(2, gameSession.level().level(), "Le niveau doit augmenter");
    }

    @Test
    void testSubmitIncorrectAnswer() {
        ILevel level = new Level(1);
        IScore score = new Score(1);
        gameSession.start(level, score);
        gameSession.nextQuestion();
        IPlayerAnswer playerAnswer = new PlayerAnswer("Mauvaise Réponse");
        gameSession.submitAnswer(playerAnswer);

        assertEquals(StateGameSessionEnum.ENDED, gameSession.state(), "La session doit être terminée");
    }

    @Test
    void testSessionPause() {
        ILevel level = new Level(1);
        IScore score = new Score(1);
        gameSession.start(level, score);
        gameSession.pause();
        assertEquals(StateGameSessionEnum.PAUSED, gameSession.state(), "La session doit être en pause");
    }

    @Test
    void testSessionEndAfterCorrectAnswer() {
        // Test explicite pour onCorrectAnswer() déjà indirectement couvert, mais on insiste.
        ILevel level = new Level(1);
        IScore score = new Score(1);
        gameSession.start(level, score);
        IQuestion question = gameSession.nextQuestion();
        IPlayerAnswer correct = new PlayerAnswer(question.correctAnswer().answer());
        gameSession.submitAnswer(correct);
        // On vérifie l'effet post soumission d'une bonne réponse (score et level déjà testés)
        assertEquals(StateGameSessionEnum.STARTED, gameSession.state(), "La session ne doit pas se terminer après une bonne réponse");
    }

}
