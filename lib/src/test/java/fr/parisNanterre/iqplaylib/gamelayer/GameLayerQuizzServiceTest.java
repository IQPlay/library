package fr.parisnanterre.iqplaylib.gamelayer;

import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.MockResponse;
import java.net.http.HttpClient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLayerQuizzServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerQuizzService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        HttpClient testClient = HttpClient.newBuilder().build();
        service = new GameLayerQuizzService(testClient);
        GameLayerService.API_URL = mockWebServer.url("/").toString();
        GameLayerService.API_KEY = "dummy-api-key";
        GameLayerService.ACCOUNT_ID = "dummy-account-id";
    }

    @Test
    void getQuizzById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\": \"quizz123\"}"));

        HttpResponse response = service.getQuizzById("quizz123", "test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getQuizzes_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("[{\"id\": \"quizz123\"}, {\"id\": \"quizz456\"}]"));

        HttpResponse response = service.getQuizzes("test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getQuizzesByPlayer_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("[{\"id\": \"quizz789\"}]"));

        HttpResponse response = service.getQuizzesByPlayer("test-player", "test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getResultOfQuizz_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"result\": \"success\"}"));

        HttpResponse response = service.getResultOfQuizz("quizz123", "test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getResultOfQuizzByPlayer_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"result\": \"success\", \"player\": \"test-player\"}"));

        HttpResponse response = service.getResultOfQuizzByPlayer("quizz123", "test-account", "test-player");
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
