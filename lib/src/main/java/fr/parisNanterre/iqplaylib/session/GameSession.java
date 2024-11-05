package fr.parisNanterre.iqplaylib.session;

import fr.parisNanterre.iqplaylib.Difficulty.strategy.interfaces.ILevel;
import fr.parisNanterre.iqplaylib.enums.StateGameSessionEnum;
import fr.parisNanterre.iqplaylib.interfaces.IGame;
import fr.parisNanterre.iqplaylib.interfaces.IGameSession;

public class GameSession implements IGameSession {

    private ILevel level;
    private IGame game;
    private StateGameSessionEnum state;

    public GameSession(IGame game) {
        this.game = game;
    }

    @Override
    public void start() {
        this.level.init();
        this.state = StateGameSessionEnum.STARTED;
    }

    @Override
    public void pause() { this.state = StateGameSessionEnum.PAUSE; }

    @Override
    public void stop() { this.state = StateGameSessionEnum.ENDED; }

    @Override
    public ILevel level() { return this.level; }

    @Override
    public StateGameSessionEnum stateGameSession() { return this.state; }

}
