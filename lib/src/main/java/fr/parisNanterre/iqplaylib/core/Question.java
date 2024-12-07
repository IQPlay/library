package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The Question class is an implementation of the IQuestion interface representing
 * a specific question in a question-answer system.
 *
 * This class holds the text of the question and its associated correct answer.
 */
public class Question implements IQuestion {
    private String questionText;
    private ICorrectAnswer correctAnswer;

    public Question(String questionText, ICorrectAnswer correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String question() {
        return questionText;
    }

    @Override
    public ICorrectAnswer correctAnswer() {
        return correctAnswer;
    }
}
