package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.io.IOException;

public interface IPlayerStreak {
    void getPlayerStreak(String player, String account) throws IOException, InterruptedException;
}
