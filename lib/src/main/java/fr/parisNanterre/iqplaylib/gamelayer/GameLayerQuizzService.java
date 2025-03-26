package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerQuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Service
public class GameLayerQuizzService extends GameLayerService implements IGameLayerQuizzService {

    @Autowired
    public GameLayerQuizzService(@Value("${api.gamelayer.key}") String apiKey,
                                  @Value("${api.gamelayer.accountId}") String accountId) {
        super(apiKey, accountId);
    }

    @Override
    public HttpResponse getQuizzById(String quizz) throws IOException, InterruptedException {
        String encodedQuizz = URLEncoder.encode(quizz, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/quizzes/"
                + encodedQuizz
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
    public HttpResponse getQuizzes() throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/quizzes" + "?account=" + encodedAccount;

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
    public HttpResponse getQuizzesByPlayer(String player) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/quizzes?"
                + "player=" + encodedPlayer
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
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }

    @Override
    public HttpResponse getResultOfQuizz(String quizz) throws IOException, InterruptedException {
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");
        String encodedQuizz = URLEncoder.encode(quizz, "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/quizzes/"
                + encodedQuizz + "/result?"
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
        super.logInformation("Status Code: " + response.statusCode());
        super.logInformation("Response Body: " + response.body());
        return response;
    }

    @Override
    public HttpResponse getResultOfQuizzByPlayer(String quizz, String player) throws IOException, InterruptedException {
        String encodedQuizz = URLEncoder.encode(quizz, "UTF-8");
        String encodedAccount = URLEncoder.encode(super.getAccountId(), "UTF-8");
        String encodedPlayer = URLEncoder.encode(player, "UTF-8");

        String fullUrl = super.getApiUrl() + "/api/v0/quizzes/"
                + encodedQuizz + "/result?"
                + "account=" + encodedAccount
                + "&player=" + encodedPlayer;

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

    /**
     * @TODO méthode à implémenter
     */
    @Override
    public HttpResponse startAQuizz(String quizz, String player) {
        return null;
    }

    /**
     * @TODO méthode à implémenter
     */
    @Override
    public HttpResponse completeQuizz(String quizz, String player, ArrayList<String> answers) {
        return null;
    }
}
