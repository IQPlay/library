package fr.parisNanterre.iqplaylib.quizz;

import fr.parisNanterre.iqplaylib.interfaces.IAnswer;

public abstract class AbstractCorrectAnswer implements IAnswer {
    private String answer;

    public AbstractCorrectAnswer(String answer) {this.answer = answer; }

    public String answer() { return answer; }
}
