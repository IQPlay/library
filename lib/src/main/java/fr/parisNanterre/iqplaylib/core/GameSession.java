package fr.parisNanterre.iqplaylib.core;

import fr.parisNanterre.iqplaylib.api.*;

/**
 * GameSession is a concrete implementation of AbstractGameSession, designed to manage the lifecycle,
 * levels, scoring, and question generation within a game session. This class provides specific logic
 * for handling correct and incorrect answers.
 */
public class GameSession extends AbstractGameSession {

    public GameSession(IGame game) {
        super(game);
    }

    @Override
    protected ILevel createLevel() {
        return new Level();
    }

    @Override
    protected IScore createScore() {
        return new Score();
    }

    @Override
    protected IQuestionStorageSession createQuestionStorageSession() {
        return new QuestionStorageSession();
    }

    @Override
    protected IQuestionGenerator createQuestionGenerator() {
        return new QuestionGenerator(new DifficultyStrategy(level));
    }

    @Override
    protected void onCorrectAnswer() {
        // Logique supplémentaire en cas de bonne réponse
    }

    @Override
    protected void onIncorrectAnswer() {
        // Logique supplémentaire en cas de mauvaise réponse
        super.onIncorrectAnswer(); // Appelle end() par défaut
    }
}
