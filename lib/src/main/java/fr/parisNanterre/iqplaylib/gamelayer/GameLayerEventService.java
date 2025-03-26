package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerEventService;
import fr.parisnanterre.iqplaylib.gamelayer.dto.player.PlayerDTO;
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
public class GameLayerEventService extends GameLayerService implements IGameLayerEventService
{

    @Autowired
    public GameLayerEventService(@Value("${api.gamelayer.key}") String apiKey,
                                       @Value("${api.gamelayer.accountId}") String accountId) {
        super(apiKey, accountId);
    }

    @Override
    public HttpResponse completeEvent(String eventId, String player) throws IOException, InterruptedException {

        HttpResponse<String> response = super.getHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(super.getApiUrl() + "/api/v0/events/" + eventId + "/complete"))
                        .headers(
                                "api-key", super.getApiKey(),
                                "Content-Type", "application/json"
                        )
                        .POST(HttpRequest.BodyPublishers.ofString(
                                super.getObjectMapper().writeValueAsString(
                                        new PlayerDTO(player, super.getAccountId())
                                )))
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() >= 400) {
            throw new RuntimeException("Échec de la requête : " + response.statusCode()
                    + "\nDétails : " + response.body());
        }
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }

    @Override
    public HttpResponse getEventById(String eventId) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(eventId, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/events/"
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
    public HttpResponse getAllEvents() throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/events" + "?account=" + encodedAccount;

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
