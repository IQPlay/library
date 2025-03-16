package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.io.IOException;

public interface IPlayerPrizes {
    void getPrizesByPlayer(String player, String account) throws IOException, InterruptedException;
}
