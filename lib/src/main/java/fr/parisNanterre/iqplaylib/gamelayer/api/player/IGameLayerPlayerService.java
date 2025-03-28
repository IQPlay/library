package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerPlayerService {
    HttpResponse createPlayer(String name, int points, int credits, String player) throws IOException, InterruptedException;

    HttpResponse getPlayerById(String player) throws IOException, InterruptedException;

    HttpResponse deletePlayerById(String player) throws IOException, InterruptedException;

//    HttpResponse updatePlayer(String playerId, String fieldToUpdate, Object value) throws IOException;
}
