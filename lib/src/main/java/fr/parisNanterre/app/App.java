package fr.parisNanterre.app;

import fr.parisNanterre.iqplaylib.enums.StateGameSessionEnum;
import fr.parisNanterre.iqplaylib.interfaces.IGame;
import fr.parisNanterre.iqplaylib.interfaces.IGameSession;
import fr.parisNanterre.iqplaylib.session.GameSession;

public class App {
    public static void main(String[] args) {

        IGame game = new CalculMentalGame();
        IGameSession gameSession = new GameSession(game);
        gameSession.start();

        while(gameSession.stateGameSession() != StateGameSessionEnum.INTERRUPTED ||
                gameSession.stateGameSession() != StateGameSessionEnum.ENDED ||
                gameSession.stateGameSession() != StateGameSessionEnum.PAUSE)
        {

        }

    }
}
