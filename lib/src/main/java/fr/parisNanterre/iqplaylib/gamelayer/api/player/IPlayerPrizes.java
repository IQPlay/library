package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IPlayerPrizes {
    HttpResponse getPrizesByPlayer(String player) throws IOException, InterruptedException;
}
