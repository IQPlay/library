package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The CorrectAnswer class implements the ICorrectAnswer interface and represents
 * a correct answer within a question-answer system.
 *
 * This class holds the text of the correct answer and provides a method to retrieve it.
 */
public class CorrectAnswer implements ICorrectAnswer {
    private String answerText;

    public CorrectAnswer(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String answer() {
        return answerText;
    }
}
