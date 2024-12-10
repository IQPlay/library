package fr.parisnanterre.iqplaylib.unit.core;

import fr.parisnanterre.iqplaylib.Score;
import fr.parisnanterre.iqplaylib.api.IScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private IScore score;

    @BeforeEach
    void setUp() {
        score = new Score(1);
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

    @Test
    void testScoreWithZeroIncrement() {
        score.incrementScore(0);
        assertEquals(0, score.score(), "Le score doit rester inchangé avec un incrément de 0");
    }

    @Test
    void testScoreWithNegativeIncrement() {
        score.incrementScore(-2);
        // Selon la logique métier, on peut décider que le score ne doit pas être négatif.
        // Ici, on laisse tel quel, mais un test permet de le mettre en évidence.
        assertEquals(-2, score.score(), "Le score peut devenir négatif si le code actuel le permet. Vérifier la logique métier.");
    }
}
