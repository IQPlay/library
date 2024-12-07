package fr.parisnanterre.iqplaylib.api;

/**
 * Represents an answer provided by a player within a game session.
 * This interface extends the IAnswer interface to specify that the answer
 * is from a player, differentiating it from other types of answers such as
 * correct answers.
 *
 * The IPlayerAnswer interface can be implemented to store and manage
 * the details of a player's response to a question during gameplay. Game session
 * mechanisms can then handle these responses to determine the outcome
 * and progress in the game.
 *
 * @see IAnswer
 * @see IGameSession
 * @see IQuestionStorageSession
 */
public interface IPlayerAnswer extends IAnswer {
}
