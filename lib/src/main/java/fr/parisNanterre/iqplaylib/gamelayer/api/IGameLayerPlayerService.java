package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerPlayerService {
    void createPlayer(String name, int points, int credits, String player, String account) throws IOException, InterruptedException;

    void getPlayerById(String player, String account) throws IOException, InterruptedException;

    void deletePlayerById(String player, String account) throws IOException, InterruptedException;

//    void updatePlayer(String playerId, String fieldToUpdate, Object value) throws IOException;
}
