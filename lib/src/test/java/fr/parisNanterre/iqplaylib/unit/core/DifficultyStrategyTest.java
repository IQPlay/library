package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.Level;
import fr.parisNanterre.iqplaylib.api.IDifficultyStrategy;
import fr.parisNanterre.iqplaylib.api.ILevel;
import fr.parisNanterre.iqplaylib.api.IQuestion;
import fr.parisNanterre.iqplaylib.core.DifficultyStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifficultyStrategyTest {

    @Test
    void testInitAndGenerateQuestion() {
        ILevel level = new Level();
        IDifficultyStrategy strategy = new DifficultyStrategy(level);
        IQuestion question = strategy.generateQuestion();
        assertTrue(question.question().contains("niveau 1"), "La question doit refléter le niveau initial");
    }

    @Test
    void testUpdateDifficultyStrategy() {
        ILevel level = new Level();
        IDifficultyStrategy strategy = new DifficultyStrategy(level);

        // Simuler une montée de niveau
        level.levelUp();
        strategy.update(level);
        IQuestion questionNiveau2 = strategy.generateQuestion();
        assertTrue(questionNiveau2.question().contains("niveau 2"), "La question doit refléter le changement de niveau");
    }
}
