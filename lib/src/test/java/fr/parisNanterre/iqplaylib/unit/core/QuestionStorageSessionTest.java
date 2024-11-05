package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.CorrectAnswer;
import fr.parisNanterre.iqplaylib.core.DefaultPlayerAnswer;
import fr.parisNanterre.iqplaylib.core.DefaultQuestion;
import fr.parisNanterre.iqplaylib.core.DefaultQuestionStorageSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultQuestionStorageSessionTest {

    private IQuestionStorageSession storageSession;

    @BeforeEach
    void setUp() {
        storageSession = new DefaultQuestionStorageSession();
    }

    @Test
    void testAddAndGetQuestion() {
        IQuestion question = new DefaultQuestion("Test Question", new CorrectAnswer("Test Answer"));
        storageSession.addQuestion(question);

        assertEquals(1, storageSession.getQuestions().size(), "Il doit y avoir une question stockée");
        assertEquals(question, storageSession.getLastQuestion(), "La dernière question doit correspondre à celle ajoutée");
    }

    @Test
    void testAddAndGetPlayerAnswer() {
        IPlayerAnswer playerAnswer = new DefaultPlayerAnswer("Player Answer");
        storageSession.addPlayerAnswer(playerAnswer);

        assertEquals(1, storageSession.getPlayerAnswers().size(), "Il doit y avoir une réponse du joueur stockée");
        assertEquals(playerAnswer, storageSession.getPlayerAnswers().get(0), "La réponse du joueur doit correspondre à celle ajoutée");
    }
}
