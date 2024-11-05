package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.api.ILevel;
import fr.parisNanterre.iqplaylib.core.DefaultLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultLevelTest {

    private ILevel level;

    @BeforeEach
    void setUp() {
        level = new DefaultLevel();
        level.init();
    }

    @Test
    void testInitialLevel() {
        assertEquals(1, level.getLevel(), "Le niveau initial doit être 1");
    }

    @Test
    void testLevelUp() {
        level.levelUp();
        assertEquals(2, level.getLevel(), "Après un levelUp, le niveau doit être 2");
    }

    @Test
    void testLevelDown() {
        level.levelUp();
        level.levelDown();
        assertEquals(1, level.getLevel(), "Après un levelUp puis un levelDown, le niveau doit revenir à 1");
    }

    @Test
    void testLevelDownAtMinimum() {
        level.levelDown();
        assertEquals(1, level.getLevel(), "Le niveau ne doit pas descendre en dessous de 1");
    }
}
