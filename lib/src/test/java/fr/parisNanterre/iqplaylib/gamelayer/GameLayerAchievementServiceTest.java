package fr.parisnanterre.iqplaylib.gamelayer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLayerAchievementServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerAchievementService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        HttpClient testClient = HttpClient.newBuilder().build();
        service = new GameLayerAchievementService(testClient);
        GameLayerService.API_URL = mockWebServer.url("/").toString();
        GameLayerService.API_KEY = "dummy-api-key";
        GameLayerService.ACCOUNT_ID = "dummy-account-id";
    }

    @Test
    void getAchievementById_Test() throws Exception {
        String responseBody = "{\"id\": \"achiev123\", \"name\": \"Achievement Test\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getAchievementById("achiev123", "test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getAllAchievements_Test() throws Exception {
        String responseBody = "[{\"id\": \"achiev123\"}, {\"id\": \"achiev456\"}]";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getAllAchievements("test-account");
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
