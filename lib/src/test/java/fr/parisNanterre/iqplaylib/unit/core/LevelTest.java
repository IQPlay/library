package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.Level;
import fr.parisNanterre.iqplaylib.api.ILevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    private ILevel level;

    @BeforeEach
    void setUp() {
        level = new Level();
    }

    @Test
    void testInitialLevel() {
        assertEquals(1, level.level(), "Le niveau initial doit être 1");
    }

    @Test
    void testLevelUp() {
        level.levelUp();
        assertEquals(2, level.level(), "Après un levelUp, le niveau doit être 2");
    }

    @Test
    void testLevelDown() {
        level.levelUp();
        level.levelDown();
        assertEquals(1, level.level(), "Après un levelUp puis un levelDown, le niveau doit revenir à 1");
    }

    @Test
    void testLevelDownAtMinimum() {
        level.levelDown();
        assertEquals(1, level.level(), "Le niveau ne doit pas descendre en dessous de 1");
    }

    @Test
    void testMultipleLevelDownAtMinimum() {
        level.levelDown();
        level.levelDown();
        assertEquals(1, level.level(), "Le niveau doit rester à 1 malgré plusieurs levelDown");
    }
}
