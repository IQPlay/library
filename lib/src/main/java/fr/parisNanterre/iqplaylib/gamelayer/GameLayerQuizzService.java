package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerQuizzService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GameLayerQuizzService extends GameLayerService implements IGameLayerQuizzService {

    public GameLayerQuizzService() {
        super();
    }

    public GameLayerQuizzService(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    public HttpResponse getQuizzById(String quizz, String account) throws IOException, InterruptedException {
        String encodedQuizz = URLEncoder.encode(quizz, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/quizzes/"
                + encodedQuizz
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
    public HttpResponse getQuizzes(String account) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(account, "UTF-8");

        String fullUrl = API_URL + "/api/v0/quizzes" + "?account=" + encodedAccount;

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
    public HttpResponse getQuizzesByPlayer(String player, String account) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(account, "UTF-8");
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");

        String fullUrl = API_URL + "/api/v0/quizzes?"
                + "player=" + encodedPlayer
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
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }

    @Override
    public HttpResponse getResultOfQuizz(String quizz, String account) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(account, "UTF-8");
        String encodedQuizz = URLEncoder.encode(quizz, "UTF-8");

        String fullUrl = API_URL + "/api/v0/quizzes/"
                + encodedQuizz + "/result?"
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
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }

    @Override
    public HttpResponse getResultOfQuizzByPlayer(String quizz, String account, String player) throws IOException, InterruptedException {
        String encodedQuizz = URLEncoder.encode(quizz, "UTF-8");
        String encodedAccount = URLEncoder.encode(account, "UTF-8");
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");

        String fullUrl = API_URL + "/api/v0/quizzes/"
                + encodedQuizz + "/result?"
                + "account=" + encodedAccount
                + "&player=" + encodedPlayer;

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
    public HttpResponse startAQuizz(String quizz, String account, String player) {
        return null;
    }

    @Override
    public HttpResponse completeQuizz(String quizz, String account, String player, ArrayList<String> answers) {
        return null;
    }
}
