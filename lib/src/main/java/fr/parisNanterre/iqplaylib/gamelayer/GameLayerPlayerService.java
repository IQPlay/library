package fr.parisnanterre.iqplaylib.gamelayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerPlayerService;
import fr.parisnanterre.iqplaylib.gamelayer.dto.NewPlayerDTO;
import fr.parisnanterre.iqplaylib.gamelayer.dto.PlayerDTO;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class GameLayerPlayerService extends GameLayerService implements IGameLayerPlayerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

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

}
