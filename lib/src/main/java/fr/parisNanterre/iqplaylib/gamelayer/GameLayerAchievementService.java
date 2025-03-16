package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerAchievementService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GameLayerAchievementService extends GameLayerService implements IGameLayerAchievementService {

    @Override
    public HttpResponse getAchievementById(String achievementId, String account) throws IOException, InterruptedException {
        String encodedachievementId = URLEncoder.encode(achievementId, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/achievements/"
                + encodedachievementId
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
    public HttpResponse getAllAchievements(String account) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/achievements" + "?account=" + encodedAccount;

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
