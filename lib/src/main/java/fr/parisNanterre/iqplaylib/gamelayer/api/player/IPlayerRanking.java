package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.net.http.HttpResponse;

public interface IPlayerRanking {
    HttpResponse getRankingByPlayer(String player) throws Exception, InterruptedException;
}
