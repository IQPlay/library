package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerMissionService;
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
public class GameLayerMissionService extends GameLayerService implements IGameLayerMissionService
{

    @Autowired
    public GameLayerMissionService(@Value("${api.gamelayer.key}") String apiKey,
                                  @Value("${api.gamelayer.accountId}") String accountId) {
        super(apiKey, accountId);
    }

    @Override
    public HttpResponse getMissionById(String missionId) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(missionId, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/missions/"
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
    public HttpResponse getAllMissions() throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/missions" + "?account=" + encodedAccount;

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
