package fr.parisNanterre.iqplaylib.core;

import fr.parisNanterre.iqplaylib.api.*;

/**
 * This class represents a level management system implementing the ILevel interface.
 * It allows initialization and adjustment of the current level up or down, with a minimum level of 1.
 */
public class Level implements ILevel {
    private int currentLevel;

    @Override
    public void init() {
        this.currentLevel = 1;
    }

    @Override
    public int level() {
        return currentLevel;
    }

    @Override
    public void levelUp() {
        currentLevel++;
    }

    @Override
    public void levelDown() {
        if (currentLevel > 1) {
            currentLevel--;
        }
    }
}
