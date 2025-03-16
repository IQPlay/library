package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.io.IOException;

public interface IPlayerAchivement {
    void getAchivementsByPlayer(String player, String account) throws IOException, InterruptedException;
}
