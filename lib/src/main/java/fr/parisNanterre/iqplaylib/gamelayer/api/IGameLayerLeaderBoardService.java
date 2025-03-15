package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerLeaderBoardService {
    String getLeaderboardById(String id) throws IOException;

    String getAllLeaderboards() throws IOException;

    String getLevelById(String id) throws IOException;

    String getAllLevels() throws IOException;
}
