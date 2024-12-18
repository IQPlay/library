package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisnanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.CorrectAnswer;
import fr.parisNanterre.iqplaylib.core.PlayerAnswer;
import fr.parisNanterre.iqplaylib.core.Question;
import fr.parisNanterre.iqplaylib.core.QuestionStorageSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionStorageSessionTest {

    private IQuestionStorageSession storageSession;

    @BeforeEach
    void setUp() {
        storageSession = new QuestionStorageSession();
    }

    @Test
    void testAddAndGetQuestion() {
        IQuestion question = new Question("Test Question", new CorrectAnswer("Test Answer"));
        storageSession.addQuestion(question);

        assertEquals(1, storageSession.questions().size(), "Il doit y avoir une question stockée");
        assertEquals(question, storageSession.lastQuestion(), "La dernière question doit correspondre à celle ajoutée");
    }

    @Test
    void testAddAndGetPlayerAnswer() {
        IPlayerAnswer playerAnswer = new PlayerAnswer("Player Answer");
        storageSession.addPlayerAnswer(playerAnswer);

        assertEquals(1, storageSession.playerAnswers().size(), "Il doit y avoir une réponse du joueur stockée");
        assertEquals(playerAnswer, storageSession.playerAnswers().get(0), "La réponse du joueur doit correspondre à celle ajoutée");
    }
}
