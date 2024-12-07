package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The QuestionGenerator class is responsible for generating questions
 * based on a specified difficulty strategy. It implements the
 * IQuestionGenerator interface, which defines the contract for
 * question generation in the system.
 *
 * The constructor of this class takes an IDifficultyStrategy
 * object, which it uses to generate questions according to the
 * provided strategy.
 *
 * The generateQuestion method utilizes the difficulty strategy's
 * generateQuestion method to create and return an IQuestion object.
 */
public class QuestionGenerator implements IQuestionGenerator {
    private IDifficultyStrategy difficultyStrategy;

    public QuestionGenerator(IDifficultyStrategy difficultyStrategy) {
        this.difficultyStrategy = difficultyStrategy;
    }

    @Override
    public IQuestion generateQuestion() {
        return difficultyStrategy.generateQuestion();
    }
}
