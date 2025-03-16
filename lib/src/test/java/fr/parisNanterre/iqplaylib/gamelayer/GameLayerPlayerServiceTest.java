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

public class GameLayerPlayerServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerPlayerService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        HttpClient testClient = HttpClient.newBuilder().build();
        service = new GameLayerPlayerService(testClient);
        GameLayerService.API_URL = mockWebServer.url("/").toString();
        GameLayerService.API_KEY = "dummy-api-key";
    }

    @Test
    void createPlayer_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody("{\"id\": \"123\"}"));

        HttpResponse response = service.createPlayer("JohnDoe", 100, 50, "player123", "test-account");
        assertEquals(201, response.statusCode());
    }

    @Test
    void getPlayerById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody("{\"id\": \"player123\", \"name\": \"JohnDoe\"}"));

        HttpResponse response = service.getPlayerById("player123", "test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void deletePlayerById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        HttpResponse response = service.deletePlayerById("player123", "test-account");
        assertEquals(204, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
