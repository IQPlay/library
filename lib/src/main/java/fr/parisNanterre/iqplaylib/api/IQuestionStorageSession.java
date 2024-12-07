package fr.parisNanterre.iqplaylib.api;

import java.util.List;

/**
 * IQuestionStorageSession is an interface that defines the methods required for
 * managing the storage and retrieval of questions, answers, and scores within a session.
 */
public interface IQuestionStorageSession {
    IQuestion lastQuestion();
    List<IQuestion> questions();
    List<ICorrectAnswer> correctAnswers();
    List<IPlayerAnswer> playerAnswers();
    IScore score();
    void addQuestion(IQuestion question);
    void addPlayerAnswer(IPlayerAnswer answer);
}
