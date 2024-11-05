package fr.parisNanterre.iqplaylib.quizz;

import fr.parisNanterre.iqplaylib.interfaces.IQuestion;

public class Question implements IQuestion {
    private String question;

    public Question(String question) {this.question = question; }

    public String question() { return question; }

}
