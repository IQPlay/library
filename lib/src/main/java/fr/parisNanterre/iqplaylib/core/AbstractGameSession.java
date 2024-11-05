package fr.parisNanterre.iqplaylib.core;

import fr.parisNanterre.iqplaylib.api.*;

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

    public AbstractGameSession(IGame game) {
        this.game = game;
        this.level = createLevel();
        this.score = createScore();
        this.questionStorage = createQuestionStorageSession();
        this.questionGenerator = createQuestionGenerator();
        this.state = StateGameSessionEnum.CREATED;
    }

    protected abstract ILevel createLevel();
    protected abstract IScore createScore();
    protected abstract IQuestionStorageSession createQuestionStorageSession();
    protected abstract IQuestionGenerator createQuestionGenerator();

    @Override
    public void start() {
        this.state = StateGameSessionEnum.STARTED;
        level.init();
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
    public void submitAnswer(IPlayerAnswer answer) {
        questionStorage.addPlayerAnswer(answer);
        IQuestion lastQuestion = questionStorage.lastQuestion();
        ICorrectAnswer correctAnswer = lastQuestion.correctAnswer();

        if (correctAnswer.answer().equals(answer.answer())) {
            score.incrementScore(1);
            level.levelUp();
            onCorrectAnswer();
        } else {
            onIncorrectAnswer();
        }
    }

    protected void onCorrectAnswer() {
        // Par défaut, ne rien faire. Peut être surchargé.
    }

    protected void onIncorrectAnswer() {
        end();
    }
}
