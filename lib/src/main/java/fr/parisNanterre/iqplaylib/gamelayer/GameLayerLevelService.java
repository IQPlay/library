package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerLevelService;
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
public class GameLayerLevelService extends GameLayerService implements IGameLayerLevelService {

    @Autowired
    public GameLayerLevelService(@Value("${api.gamelayer.key}") String apiKey,
                                   @Value("${api.gamelayer.accountId}") String accountId) {
        super(apiKey, accountId);
    }

    @Override
    public HttpResponse getLevelById(String levelId) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(levelId, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/levels/"
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
    public HttpResponse getAllLevels() throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/levels" + "?account=" + encodedAccount;

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
