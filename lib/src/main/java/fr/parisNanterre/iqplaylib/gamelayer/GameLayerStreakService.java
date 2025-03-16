package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerStreakService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GameLayerStreakService extends GameLayerService implements IGameLayerStreakService {

    @Override
    public HttpResponse getStreakById(String streakId, String account) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(streakId, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/streaks/"
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
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }

    @Override
    public HttpResponse getAllStreaks(String account) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/streaks" + "?account=" + encodedAccount;

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
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }
}
