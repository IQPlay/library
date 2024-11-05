package fr.parisNanterre.iqplaylib.core;

import fr.parisNanterre.iqplaylib.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * QuestionStorageSession is a concrete implementation of the IQuestionStorageSession interface that
 * manages the storage and retrieval of questions, correct answers, player answers, and scoring within a session.
 */
public class QuestionStorageSession implements IQuestionStorageSession {
    private List<IQuestion> questions;
    private List<ICorrectAnswer> correctAnswers;
    private List<IPlayerAnswer> playerAnswers;
    private IScore score;

    public QuestionStorageSession() {
        this.questions = new ArrayList<>();
        this.correctAnswers = new ArrayList<>();
        this.playerAnswers = new ArrayList<>();
        this.score = new Score();
    }

    @Override
    public IQuestion lastQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        return questions.get(questions.size() - 1);
    }

    @Override
    public List<IQuestion> questions() {
        return new ArrayList<>(questions);
    }

    @Override
    public List<ICorrectAnswer> correctAnswers() {
        return new ArrayList<>(correctAnswers);
    }

    @Override
    public List<IPlayerAnswer> playerAnswers() {
        return new ArrayList<>(playerAnswers);
    }

    @Override
    public IScore score() {
        return score;
    }

    public void addQuestion(IQuestion question) {
        questions.add(question);
        correctAnswers.add(question.correctAnswer());
    }

    public void addPlayerAnswer(IPlayerAnswer answer) {
        playerAnswers.add(answer);
    }
}
