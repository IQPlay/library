package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerLeaderBoardService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GameLayerLeaderBoardService extends GameLayerService implements IGameLayerLeaderBoardService {

    /** cette méthode bug, l'implémentation est correcte, l'url et les paramètres sont correcte mais le serveur ne trouve
     * pas la ressource demandée
     */
    @Override
    public void getLeaderboardById(String leaderboardId, String account) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(leaderboardId, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/leaderboards/"
                + encodedEventId
                + "?account=" + encodedAccount
                + "&language=fr";

        HttpResponse response = httpClient.send(
                HttpRequest.newBuilder()
                        .uri(URI.create(fullUrl))
                        .headers(
                                "api-key", API_KEY
                        )
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    @Override
    public void getAllLeaderboards(String account) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/leaderboards" + "?account=" + encodedAccount;

        HttpResponse response = httpClient.send(
                HttpRequest.newBuilder()
                        .uri(URI.create(fullUrl))
                        .headers(
                                "api-key", API_KEY
                        )
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());;
    }
}
