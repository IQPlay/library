package fr.parisnanterre.iqplaylib.api;

/**
 * IQuestion is an interface representing a question in a question-answer system.
 * Implementations of this interface should provide mechanisms to retrieve the
 * text of the question and the correct answer associated with it.
 *
 * The question() method returns the text of the question.
 * The correctAnswer() method returns the correct answer for the question,
 * which is represented by an ICorrectAnswer object.
 *
 * This interface can be implemented in various contexts such as quiz games,
 * educational applications, or any other system requiring a question-answer feature.
 */
public interface IQuestion {
    String question();
    ICorrectAnswer correctAnswer();
}
