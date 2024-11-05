package fr.parisNanterre.iqplaylib.quizz;

import fr.parisNanterre.iqplaylib.interfaces.IAnswer;

public abstract class AbstractPlayerAnswer implements IAnswer {
    private String answer;

    public AbstractPlayerAnswer(String answer) {this.answer = answer; }

    public String answer() { return answer; }
}
