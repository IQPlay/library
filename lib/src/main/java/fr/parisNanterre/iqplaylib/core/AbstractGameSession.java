package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * Abstract class that defines the blueprint for a game session.
 * Implements IGameSession.
 */
public abstract class AbstractGameSession implements IGameSession {
    protected IGame game;
    protected ILevel level;
    protected IScore score;
    protected IQuestionGenerator questionGenerator;
    protected IQuestionStorageSession questionStorage;
    protected StateGameSessionEnum state;

    protected AbstractGameSession(IGame game) {
        this.game = game;
        this.questionStorage = createQuestionStorageSession();
        this.questionGenerator = createQuestionGenerator();
        this.state = StateGameSessionEnum.CREATED;
    }

    protected abstract IQuestionStorageSession createQuestionStorageSession();
    protected abstract IQuestionGenerator createQuestionGenerator();

    @Override
    public void start(ILevel level, IScore score) {
        this.state = StateGameSessionEnum.STARTED;
        this.level = level;
        this.score = score;
    }

    @Override
    public void resume() {
        this.state = StateGameSessionEnum.IN_PROGRESS;
    }

    @Override
    public void pause() {
        this.state = StateGameSessionEnum.PAUSED;
    }

    @Override
    public void end() {
        this.state = StateGameSessionEnum.ENDED;
    }

    @Override
    public ILevel level() {
        return level;
    }

    @Override
    public IScore score() {
        return score;
    }

    @Override
    public StateGameSessionEnum state() {
        return state;
    }

    @Override
    public IQuestion nextQuestion() {
        IQuestion question = questionGenerator.generateQuestion();
        questionStorage.addQuestion(question);
        return question;
    }

    @Override
    public boolean submitAnswer(IPlayerAnswer answer) {
        questionStorage.addPlayerAnswer(answer);
        IQuestion lastQuestion = questionStorage.lastQuestion();
        ICorrectAnswer correctAnswer = lastQuestion.correctAnswer();

        if (correctAnswer.answer().equals(answer.answer())) {
            score.incrementScore(1);
            level.levelUp();
            onCorrectAnswer();
            return true;
        } else {
            onIncorrectAnswer();
            return false;
        }
    }

    protected void onCorrectAnswer() {
        // Par défaut, ne rien faire. Peut être surchargé.
    }

    protected void onIncorrectAnswer() {
        end();
    }
}
