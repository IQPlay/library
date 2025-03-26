package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerStreakService;
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
public class GameLayerStreakService extends GameLayerService implements IGameLayerStreakService {


    @Autowired
    public GameLayerStreakService(@Value("${api.gamelayer.key}") String apiKey,
                                 @Value("${api.gamelayer.accountId}") String accountId) {
        super(apiKey, accountId);
    }


    @Override
    public HttpResponse getStreakById(String streakId) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(streakId, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/streaks/"
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
    public HttpResponse getAllStreaks() throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/streaks" + "?account=" + encodedAccount;

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
