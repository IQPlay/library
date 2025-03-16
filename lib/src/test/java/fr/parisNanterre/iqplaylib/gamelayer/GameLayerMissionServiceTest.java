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

public class GameLayerMissionServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerMissionService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        HttpClient testClient = HttpClient.newBuilder().build();
        service = new GameLayerMissionService(testClient);
        GameLayerService.API_URL = mockWebServer.url("/").toString();
        GameLayerService.API_KEY = "dummy-api-key";
    }

    @Test
    void getMissionById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"id\": \"mission-123\"}"));

        HttpResponse response = service.getMissionById("mission-123", "test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getAllMissions_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[{\"id\": \"mission-123\"}, {\"id\": \"mission-456\"}]")
        );

        HttpResponse response = service.getAllMissions("test-account");
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
