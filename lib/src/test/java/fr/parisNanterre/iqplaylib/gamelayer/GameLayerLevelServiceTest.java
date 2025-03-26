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

public class GameLayerLevelServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerLevelService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        GameLayerLevelService realService = new GameLayerLevelService("dummy-key", "dummy-account");
        service = Mockito.spy(realService);

        when(service.getApiUrl()).thenReturn(mockWebServer.url("/").toString());
    }

    @Test
    void getLevelById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"id\": \"123\"}")
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getLevelById("123");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getAllLevels_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("[{\"id\": \"123\"}]")
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getAllLevels();
        assertEquals(200, response.statusCode());
    }

    @Test
    void getLevelById_NotFound_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(404)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getLevelById("999");
        assertEquals(404, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}