package fr.parisnanterre.iqplaylib.api;

/**
 * Represents a game session, providing control over the session's lifecycle and interactions within it.
 */
public interface IGameSession {
    void start();
    void pause();
    void end();
    ILevel level();
    IScore score();
    StateGameSessionEnum state();
    IQuestion nextQuestion();
    void submitAnswer(IPlayerAnswer answer);
    IQuestionStorageSession questionStorage();
}