package fr.parisNanterre.iqplaylib.interfaces;

import java.util.Set;

public interface IQuestionStorageSession {
    IQuestion lastQuestion();
    Set<IQuestion> questions();
    Set<ICorrectAnswer> correctAnswers();
    Set<IPlayerAnswer> playerAnswers();
    IScore score();
}
