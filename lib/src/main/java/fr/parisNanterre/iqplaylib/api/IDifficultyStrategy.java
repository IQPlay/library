package fr.parisNanterre.iqplaylib.api;

/**
 * The IDifficultyStrategy interface defines the contract for implementing
 * different difficulty strategies in a question-generation system.
 *
 */
public interface IDifficultyStrategy {
    void init(ILevel level);
    void update(ILevel level);
    IQuestion generateQuestion();
}
