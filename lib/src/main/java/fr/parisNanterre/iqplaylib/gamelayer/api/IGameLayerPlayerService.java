package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerPlayerService {
    String createPlayer(String name, String team, int points, int credits, String levelId, String levelName) throws IOException;

    String getPlayerById(String playerId) throws IOException;

    void deletePlayerById(String playerId) throws IOException;

    String updatePlayer(String playerId, String fieldToUpdate, Object value) throws IOException;

    String getPlayerLevels(String playerId) throws IOException;
}
