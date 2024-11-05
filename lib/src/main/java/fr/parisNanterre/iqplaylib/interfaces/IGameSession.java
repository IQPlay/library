package fr.parisNanterre.iqplaylib.interfaces;

import fr.parisNanterre.iqplaylib.Difficulty.strategy.interfaces.ILevel;
import fr.parisNanterre.iqplaylib.enums.StateGameSessionEnum;

public interface IGameSession {
    void start();
    void pause();
    void stop();
    ILevel level();
    StateGameSessionEnum stateGameSession();
}
