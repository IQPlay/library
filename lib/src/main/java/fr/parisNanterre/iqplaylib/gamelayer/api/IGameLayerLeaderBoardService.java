package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerLeaderBoardService {
    HttpResponse getLeaderboardById(String leaderboardId) throws IOException, InterruptedException;

    HttpResponse getAllLeaderboards() throws IOException, InterruptedException;

}
