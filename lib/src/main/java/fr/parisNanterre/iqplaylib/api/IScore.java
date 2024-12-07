package fr.parisnanterre.iqplaylib.api;

/**
 * The IScore interface defines the contract for managing a score within a system.
 * This interface provides methods to retrieve the current score and to increment the score by a specified value.
 */
public interface IScore {
    int score();
    void incrementScore(int value);
}