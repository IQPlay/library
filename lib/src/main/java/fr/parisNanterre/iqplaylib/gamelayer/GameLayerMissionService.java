package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerMissionService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GameLayerMissionService extends GameLayerService implements IGameLayerMissionService
{
    @Override
    public HttpResponse getMissionById(String missionId, String account) throws IOException, InterruptedException {
        String encodedEventId = URLEncoder.encode(missionId, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/missions/"
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
    public HttpResponse getAllMissions(String account) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/missions" + "?account=" + encodedAccount;

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
