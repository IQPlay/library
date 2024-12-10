package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * This class represents a level management system implementing the ILevel interface.
 * It allows initialization and adjustment of the current level up or down, with a minimum level of 1.
 */
public abstract class AbstractLevel implements ILevel {
    protected int currentLevel;

    public AbstractLevel(int currentLevel) {
        this.currentLevel = currentLevel;
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
