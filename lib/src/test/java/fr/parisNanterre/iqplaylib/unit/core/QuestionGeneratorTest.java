package fr.parisnanterre.iqplaylib.unit.core;

import fr.parisnanterre.iqplaylib.Level;
import fr.parisnanterre.iqplaylib.api.*;
import fr.parisnanterre.iqplaylib.core.DifficultyStrategy;
import fr.parisnanterre.iqplaylib.core.QuestionGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionGeneratorTest {

    @Test
    void testGenerateQuestion() {
        ILevel level = new Level();

        IDifficultyStrategy difficultyStrategy = new DifficultyStrategy(level);
        IQuestionGenerator questionGenerator = new QuestionGenerator(difficultyStrategy);

        IQuestion question = questionGenerator.generateQuestion();

        assertNotNull(question, "La question générée ne doit pas être nulle");
        assertNotNull(question.question(), "Le texte de la question ne doit pas être nul");
        assertNotNull(question.correctAnswer(), "La réponse correcte ne doit pas être nulle");
    }
}
