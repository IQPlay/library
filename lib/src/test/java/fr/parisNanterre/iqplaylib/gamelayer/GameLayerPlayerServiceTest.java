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

public class GameLayerPlayerServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerPlayerService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        GameLayerPlayerService realService = new GameLayerPlayerService("dummy-key", "dummy-account");
        service = Mockito.spy(realService);

        // Mock de la méthode getApiUrl() pour retourner l'URL du mock server
        when(service.getApiUrl()).thenReturn(mockWebServer.url("/").toString());
    }

    @Test
    void createPlayer_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody("{\"id\": \"123\"}"));

        HttpResponse<?> response = service.createPlayer("JohnDoe", 100, 50, "player123");
        assertEquals(201, response.statusCode());
    }

    @Test
    void getPlayerById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"id\": \"player123\"}"));

        HttpResponse<?> response = service.getPlayerById("player123");
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}