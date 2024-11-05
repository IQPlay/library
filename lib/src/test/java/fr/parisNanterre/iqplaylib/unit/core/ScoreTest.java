package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.IScore;
import fr.parisNanterre.iqplaylib.core.DefaultScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultScoreTest {

    private IScore score;

    @BeforeEach
    void setUp() {
        score = new DefaultScore();
    }

    @Test
    void testInitialScore() {
        assertEquals(0, score.getScoreValue(), "Le score initial doit être 0");
    }

    @Test
    void testIncrementScore() {
        score.incrementScore(5);
        assertEquals(5, score.getScoreValue(), "Après incrémentation, le score doit être 5");
    }

    @Test
    void testMultipleIncrements() {
        score.incrementScore(2);
        score.incrementScore(3);
        assertEquals(5, score.getScoreValue(), "Après plusieurs incrémentations, le score doit être 5");
    }
}
