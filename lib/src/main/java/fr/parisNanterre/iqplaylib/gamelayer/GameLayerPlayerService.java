package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.player.*;
import fr.parisnanterre.iqplaylib.gamelayer.dto.player.NewPlayerDTO;
import fr.parisnanterre.iqplaylib.gamelayer.dto.player.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameLayerPlayerService extends GameLayerService implements IGameLayerPlayerService, IPlayerAchivement, IPlayerEvent, IPlayerLevel, IPlayerMission, IPlayerPrizes, IPlayerRanking, IPlayerStreak {


    @Autowired
    public GameLayerPlayerService(@Value("${api.gamelayer.key}") String apiKey,
                                  @Value("${api.gamelayer.accountId}") String accountId) {
        super(apiKey, accountId);
    }


    /**
     * Crée un joueur sur GameLayer.
     */
    @Override
    public HttpResponse createPlayer(String name, int points, int credits, String player)
            throws IOException, InterruptedException {

        HttpResponse<String> response = super.getHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(super.getApiUrl() + "/api/v0/players"))
                        .headers(
                                "api-key", super.getApiKey(),
                                "Content-Type", "application/json"
                        )
                        .POST(HttpRequest.BodyPublishers.ofString(
                                super.getObjectMapper().writeValueAsString(
                                        new NewPlayerDTO(name, points, credits, player, super.getAccountId())
                                )))
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        if (response.statusCode() >= 400) {
            throw new RuntimeException("Échec de la requête : " + response.statusCode()
                    + "\nDétails : " + response.body());
        }
        return response;
    }

    /**
     * Récupère les informations d'un joueur par son ID.
     */
    @Override
    public HttpResponse getPlayerById(String player) throws IOException, InterruptedException {

        PlayerDTO playerDTO = new PlayerDTO(player, super.getAccountId());
        String encodedPlayer = URLEncoder.encode(playerDTO.getPlayer(), "UTF-8");
        String encodedAccount = URLEncoder.encode(playerDTO.getAccount(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + "?player=" + encodedPlayer
                + "&account=" + encodedAccount;

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
        return response;
    }


    /**
     * bug dans l'uri pour supprimer un utilisateur.
     * l'api n'a pas besoin du paramètre 'id' explicitement dans l'uri (id=player) il faut passer directement le player
     * eg : /api/v0/players/3?account=agence-recherche
     */
    @Override
    public HttpResponse deletePlayerById(String player) throws IOException, InterruptedException {
        PlayerDTO playerDTO = new PlayerDTO(player, super.getAccountId());
        String encodedPlayer = URLEncoder.encode(playerDTO.getPlayer(), "UTF-8");
        String encodedAccount = URLEncoder.encode(playerDTO.getAccount(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer
                + "?account=" + encodedAccount;

        Map<String, Boolean> bodyData = new HashMap<>();
        bodyData.put("hardDelete", false);
        String requestBody = super.getObjectMapper().writeValueAsString(bodyData);

        HttpResponse response = super.getHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(fullUrl))
                        .headers("api-key", super.getApiKey())
                        .header("Content-Type", "application/json")
                        .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }


    @Override
    public HttpResponse getAchivementsByPlayer(String player) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer + "/achievements"
                + "?account=" + encodedAccount;

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
    public HttpResponse getEventsByPlayer(String player) throws Exception, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer + "/events"
                + "?account=" + encodedAccount;

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
    public HttpResponse getLevelsByPlayer(String player) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer + "/levels"
                + "?account=" + encodedAccount;

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
    public HttpResponse getMissionByPlayerId(String player) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer + "/missions"
                + "?account=" + encodedAccount;

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
    public HttpResponse getMissionByPlayerIdAndMissionId(String player, String missionId) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");
        String encodedMissionId = URLEncoder.encode(missionId, "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer + "/missions/"
                + encodedMissionId
                + "?account=" + encodedAccount;

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
    public HttpResponse getPrizesByPlayer(String player) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer + "/prizes"
                + "?account=" + encodedAccount;

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
    public HttpResponse getRankingByPlayer(String player) throws Exception, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer + "/ranking"
                + "?account=" + encodedAccount;

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
    public HttpResponse getPlayerStreak(String player) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/players/"
                + encodedPlayer + "/streaks"
                + "?account=" + encodedAccount;

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
