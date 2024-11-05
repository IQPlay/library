package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.DifficultyStrategy;
import fr.parisNanterre.iqplaylib.core.Level;
import fr.parisNanterre.iqplaylib.core.QuestionGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionGeneratorTest {

    @Test
    void testGenerateQuestion() {
        ILevel level = new Level();
        level.init();

        IDifficultyStrategy difficultyStrategy = new DifficultyStrategy(level);
        IQuestionGenerator questionGenerator = new QuestionGenerator(difficultyStrategy);

        IQuestion question = questionGenerator.generateQuestion();

        assertNotNull(question, "La question générée ne doit pas être nulle");
        assertNotNull(question.question(), "Le texte de la question ne doit pas être nul");
        assertNotNull(question.correctAnswer(), "La réponse correcte ne doit pas être nulle");
    }
}
