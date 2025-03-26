package fr.parisnanterre.iqplaylib.gamelayer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameLayerAchievementServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerAchievementService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        GameLayerAchievementService realService = new GameLayerAchievementService("dummy-key", "dummy-account");

        service = Mockito.spy(realService);

        when(service.getApiUrl()).thenReturn(mockWebServer.url("/").toString());
    }

    @Test
    void getAchievementById_Test() throws Exception {
        String responseBody = "{\"id\": \"achiev123\", \"name\": \"Achievement Test\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getAchievementById("achiev123");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getAllAchievements_Test() throws Exception {
        String responseBody = "[{\"id\": \"achiev123\"}, {\"id\": \"achiev456\"}]";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getAllAchievements();
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}