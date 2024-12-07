package fr.parisnanterre.iqplaylib.api;

/**
 * Represents a general answer within the system.
 * This interface can be implemented to represent various types of answers,
 * such as correct answers, player answers, or any other specific answer types.
 *
 * @see ICorrectAnswer
 * @see IPlayerAnswer
 */
public interface IAnswer {
    String answer();
}