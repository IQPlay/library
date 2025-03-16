package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.io.IOException;

public interface IPlayerLevel {
    void getLevelsByPlayer(String player, String account) throws IOException, InterruptedException;
}
