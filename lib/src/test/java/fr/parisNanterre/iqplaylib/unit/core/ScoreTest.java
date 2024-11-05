package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.IScore;
import fr.parisNanterre.iqplaylib.core.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private IScore score;

    @BeforeEach
    void setUp() {
        score = new Score();
    }

    @Test
    void testInitialScore() {
        assertEquals(0, score.score(), "Le score initial doit être 0");
    }

    @Test
    void testIncrementScore() {
        score.incrementScore(5);
        assertEquals(5, score.score(), "Après incrémentation, le score doit être 5");
    }

    @Test
    void testMultipleIncrements() {
        score.incrementScore(2);
        score.incrementScore(3);
        assertEquals(5, score.score(), "Après plusieurs incrémentations, le score doit être 5");
    }
}
