package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.player.*;
import fr.parisnanterre.iqplaylib.gamelayer.dto.player.NewPlayerDTO;
import fr.parisnanterre.iqplaylib.gamelayer.dto.player.PlayerDTO;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class GameLayerPlayerService extends GameLayerService implements IGameLayerPlayerService, IPlayerAchivement, IPlayerEvent,IPlayerLevel, IPlayerMission, IPlayerPrizes, IPlayerRanking, IPlayerStreak {


    /**
     * Crée un joueur sur GameLayer.
     */
    @Override
    public void createPlayer(String name, int points, int credits,
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
    }

    /**
     * Récupère les informations d'un joueur par son ID.
     */
    @Override
    public void getPlayerById(String player, String account) throws IOException, InterruptedException {

        PlayerDTO playerDTO = new PlayerDTO(player, account);
        String encodedPlayer = URLEncoder.encode(playerDTO.getPlayer(), "UTF-8");
        String encodedAccount = URLEncoder.encode(playerDTO.getAccount(), "UTF-8");

        String fullUrl = API_URL + "/api/v0/players/"
                + "?player=" + encodedPlayer
                + "&account=" + encodedAccount;

        httpClient.send(
                HttpRequest.newBuilder()
                        .uri(URI.create(fullUrl))
                        .headers(
                                "api-key", API_KEY
                        )
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
    }


    /**
     * bug dans l'uri pour supprimer un utilisateur.
     * l'api n'a pas besoin du paramètre 'id' explicitement dans l'uri (id=player) il faut passer directement le player
     * eg : /api/v0/players/3?account=agence-recherche
     */
    @Override
    public void deletePlayerById(String player, String account) throws IOException, InterruptedException {
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
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }


    @Override
    public void getAchivementsByPlayer(String player, String account) throws IOException, InterruptedException {
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
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    @Override
    public void getEventsByPlayer(String player, String account) throws Exception, InterruptedException {
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
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    @Override
    public void getLevelsByPlayer(String player, String account) throws IOException, InterruptedException {
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
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    @Override
    public void getMissionByPlayerId(String player, String account) throws IOException, InterruptedException {
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
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    @Override
    public void getMissionByPlayerIdAndMissionId(String player, String account, String missionId) throws IOException, InterruptedException {

    }

    @Override
    public void getPrizesByPlayer(String player, String account) throws IOException, InterruptedException {

    }

    @Override
    public void getRankingByPlayer(String player, String account) throws Exception, InterruptedException {

    }

    @Override
    public void getPlayerStreak(String player, String account) throws IOException, InterruptedException {

    }
}
