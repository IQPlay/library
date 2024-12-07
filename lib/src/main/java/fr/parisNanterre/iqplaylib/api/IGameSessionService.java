package fr.parisNanterre.iqplaylib.api;

public interface IGameSessionService {
    IGameSession createSession(IPlayer player, IGame game);
    IGameSession findSession(Long sessionId);
    Long getSessionId(IGameSession session);
}
