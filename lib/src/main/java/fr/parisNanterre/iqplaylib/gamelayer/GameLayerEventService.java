package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerEventService;
import fr.parisnanterre.iqplaylib.gamelayer.dto.player.PlayerDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GameLayerEventService extends GameLayerService implements IGameLayerEventService
{

    @Override
    public HttpResponse completeEvent(String eventId, String player, String account) throws IOException, InterruptedException {

        HttpResponse<String> response = httpClient.send(
                HttpRequest.newBuilder()
                        .uri(URI.create(API_URL + "/api/v0/events/" + eventId + "/complete"))
                        .headers(
                                "api-key", API_KEY,
                                "Content-Type", "application/json"
                        )
                        .POST(HttpRequest.BodyPublishers.ofString(
                                objectMapper.writeValueAsString(
                                        new PlayerDTO(player, account)
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
    public HttpResponse getEventById(String eventId, String account) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(eventId, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/events/"
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
    public HttpResponse getAllEvents(String account) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/events" + "?account=" + encodedAccount;

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
