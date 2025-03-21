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

public class GameLayerStreakServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerStreakService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        HttpClient testClient = HttpClient.newBuilder().build();
        service = new GameLayerStreakService(testClient);
        GameLayerService.API_URL = mockWebServer.url("/").toString();
        GameLayerService.API_KEY = "dummy-api-key";
        GameLayerService.ACCOUNT_ID = "dummy-account-id";
    }


    @Test
    void getStreakById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\": \"123\"}"));

        HttpResponse response = service.getStreakById("123", "test-account");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getAllStreaks_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\": \"123\"}"));

        HttpResponse response = service.getAllStreaks("test-account");
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

}
