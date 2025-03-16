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

public class GameLayerLevelServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerLevelService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        HttpClient testClient = HttpClient.newBuilder().build();
        service = new GameLayerLevelService(testClient);
        GameLayerService.API_URL = mockWebServer.url("/").toString();
        GameLayerService.API_KEY = "dummy-api-key";
    }

    @Test
    void getLevelById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\": \"123\"}").setResponseCode(200));

        HttpResponse response = service.getLevelById("123", "test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getAllLevels_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("[{\"id\": \"123\"}]").setResponseCode(200));

        HttpResponse response = service.getAllLevels("test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getLevelById_NotFound_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        HttpResponse response = service.getLevelById("999", "test-account");
        assertEquals(404, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
