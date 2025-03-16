package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerLeaderBoardService {
    void getLeaderboardById(String leaderboardId, String account) throws IOException, InterruptedException;

    void getAllLeaderboards(String account) throws IOException, InterruptedException;

}
