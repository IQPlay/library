package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The Score class implements the IScore interface and provides functionality
 * for managing and manipulating a score value within the system.
 */
public abstract class AbstractScore implements IScore {
    private int scoreValue;

    public AbstractScore(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    @Override
    public int score() {
        return scoreValue;
    }

    @Override
    public void incrementScore(int value) {
        scoreValue += value;
    }
}
