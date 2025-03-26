package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerLeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GameLayerLeaderBoardService extends GameLayerService implements IGameLayerLeaderBoardService {

    @Autowired
    public GameLayerLeaderBoardService(@Value("${api.gamelayer.key}") String apiKey,
                                 @Value("${api.gamelayer.accountId}") String accountId) {
        super(apiKey, accountId);
    }

    /** cette méthode bug, l'implémentation est correcte, l'url et les paramètres sont correcte mais le serveur ne trouve
     * pas la ressource demandée
     */
    @Override
    public HttpResponse getLeaderboardById(String leaderboardId) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(leaderboardId, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/leaderboards/"
                + encodedEventId
                + "?account=" + encodedAccount
                + "&language=fr";

        HttpResponse response = super.getHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(fullUrl))
                        .headers(
                                "api-key", super.getApiKey()
                        )
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }

    @Override
    public HttpResponse getAllLeaderboards() throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/leaderboards" + "?account=" + encodedAccount;

        HttpResponse response = super.getHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(fullUrl))
                        .headers(
                                "api-key", super.getApiKey()
                        )
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }
}
