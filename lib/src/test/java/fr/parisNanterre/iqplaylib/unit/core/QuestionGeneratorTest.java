package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.DifficultyStrategy;
import fr.parisNanterre.iqplaylib.core.DefaultLevel;
import fr.parisNanterre.iqplaylib.core.DefaultQuestionGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultQuestionGeneratorTest {

    @Test
    void testGenerateQuestion() {
        ILevel level = new DefaultLevel();
        level.init();

        IDifficultyStrategy difficultyStrategy = new DifficultyStrategy(level);
        IQuestionGenerator questionGenerator = new DefaultQuestionGenerator(difficultyStrategy);

        IQuestion question = questionGenerator.generateQuestion();

        assertNotNull(question, "La question générée ne doit pas être nulle");
        assertNotNull(question.getQuestionText(), "Le texte de la question ne doit pas être nul");
        assertNotNull(question.getCorrectAnswer(), "La réponse correcte ne doit pas être nulle");
    }
}
