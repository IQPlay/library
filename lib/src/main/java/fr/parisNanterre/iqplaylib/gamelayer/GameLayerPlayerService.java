package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.player.*;
import fr.parisnanterre.iqplaylib.gamelayer.dto.player.NewPlayerDTO;
import fr.parisnanterre.iqplaylib.gamelayer.dto.player.PlayerDTO;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class GameLayerPlayerService extends GameLayerService implements IGameLayerPlayerService, IPlayerAchivement, IPlayerEvent,IPlayerLevel, IPlayerMission, IPlayerPrizes, IPlayerRanking, IPlayerStreak {


    public GameLayerPlayerService() {
        super();
    }

    public GameLayerPlayerService(HttpClient httpClient) {
        super(httpClient);
    }

    /**
     * Crée un joueur sur GameLayer.
     */
    @Override
    public HttpResponse createPlayer(String name, int points, int credits,
                             String player, String account)
            throws IOException, InterruptedException {

        HttpResponse<String> response = httpClient.send(
                HttpRequest.newBuilder()
                        .uri(URI.create(API_URL + "/api/v0/players"))
                        .headers(
                                "api-key", API_KEY,
                                "Content-Type", "application/json"
                        )
                        .POST(HttpRequest.BodyPublishers.ofString(
                                objectMapper.writeValueAsString(
                                        new NewPlayerDTO(name, points, credits, player, account)
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
    public HttpResponse getPlayerById(String player, String account) throws IOException, InterruptedException {

        PlayerDTO playerDTO = new PlayerDTO(player, account);
        String encodedPlayer = URLEncoder.encode(playerDTO.getPlayer(), "UTF-8");
        String encodedAccount = URLEncoder.encode(playerDTO.getAccount(), "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + "?player=" + encodedPlayer
                + "&account=" + encodedAccount;

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
        return response;
    }


    /**
     * bug dans l'uri pour supprimer un utilisateur.
     * l'api n'a pas besoin du paramètre 'id' explicitement dans l'uri (id=player) il faut passer directement le player
     * eg : /api/v0/players/3?account=agence-recherche
     */
    @Override
    public HttpResponse deletePlayerById(String player, String account) throws IOException, InterruptedException {
        PlayerDTO playerDTO = new PlayerDTO(player, account);
        String encodedPlayer = URLEncoder.encode(playerDTO.getPlayer(), "UTF-8");
        String encodedAccount = URLEncoder.encode(playerDTO.getAccount(), "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer
                + "?account=" + encodedAccount;

        Map<String, Boolean> bodyData = new HashMap<>();
        bodyData.put("hardDelete", false);
        String requestBody = objectMapper.writeValueAsString(bodyData);

        HttpResponse response =httpClient.send(
                HttpRequest.newBuilder()
                        .uri(URI.create(fullUrl))
                        .headers("api-key", API_KEY)
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
    public HttpResponse getAchivementsByPlayer(String player, String account) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer + "/achievements"
                + "?account=" + encodedAccount;

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
    public HttpResponse getEventsByPlayer(String player, String account) throws Exception, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer + "/events"
                + "?account=" + encodedAccount;

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
    public HttpResponse getLevelsByPlayer(String player, String account) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer + "/levels"
                + "?account=" + encodedAccount;

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
    public HttpResponse getMissionByPlayerId(String player, String account) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer + "/missions"
                + "?account=" + encodedAccount;

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
    public HttpResponse getMissionByPlayerIdAndMissionId(String player, String account, String missionId) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");
        String encodedMissionId = URLEncoder.encode(missionId, "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer + "/missions/"
                + encodedMissionId
                + "?account=" + encodedAccount;

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
    public HttpResponse getPrizesByPlayer(String player, String account) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer + "/prizes"
                + "?account=" + encodedAccount;

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
    public HttpResponse getRankingByPlayer(String player, String account) throws Exception, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer + "/ranking"
                + "?account=" + encodedAccount;

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
    public HttpResponse getPlayerStreak(String player, String account) throws IOException, InterruptedException {
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + encodedPlayer + "/streaks"
                + "?account=" + encodedAccount;

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
