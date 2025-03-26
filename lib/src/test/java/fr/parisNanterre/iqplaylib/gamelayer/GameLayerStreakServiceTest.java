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

public class GameLayerStreakServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerStreakService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        GameLayerStreakService realService = new GameLayerStreakService("dummy-key", "dummy-account");
        service = Mockito.spy(realService);

        when(service.getApiUrl()).thenReturn(mockWebServer.url("/").toString());
    }

    @Test
    void getStreakById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"id\": \"123\"}"));

        HttpResponse response = service.getStreakById("123");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getAllStreaks_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[{\"id\": \"123\"}, {\"id\": \"456\"}]"));

        HttpResponse response = service.getAllStreaks();
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}