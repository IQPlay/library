package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The Score class implements the IScore interface and provides functionality
 * for managing and manipulating a score value within the system.
 */
public class Score implements IScore {
    private int scoreValue;

    public Score() {
        this.scoreValue = 0;
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
