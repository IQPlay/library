package fr.parisnanterre.iqplaylib.gamelayer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class GameLayerLeaderBoardServiceTest {

    private static MockWebServer mockWebServer;
    private static GameLayerLeaderBoardService leaderBoardService;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        leaderBoardService = new GameLayerLeaderBoardService(HttpClient.newHttpClient());
        GameLayerService.API_URL = mockWebServer.url("/api/v0").toString();
        GameLayerService.API_URL = mockWebServer.url("/").toString();
        GameLayerService.API_KEY = "dummy-api-key";
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetLeaderboardById() throws IOException, InterruptedException {
        String mockResponse = "{\"id\":\"123\", \"name\":\"Top Players\"}";
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(mockResponse));

        HttpResponse response = leaderBoardService.getLeaderboardById("123", "testAccount");

        assertEquals(200, response.statusCode());
        assertEquals(mockResponse, response.body().toString());
    }

    @Test
    void testGetAllLeaderboards() throws IOException, InterruptedException {
        String mockResponse = "[{\"id\":\"123\", \"name\":\"Top Players\"}, {\"id\":\"124\", \"name\":\"Weekly Winners\"}]";
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(mockResponse));

        HttpResponse response = leaderBoardService.getAllLeaderboards("testAccount");

        assertEquals(200, response.statusCode());
        assertEquals(mockResponse, response.body().toString());
    }
}
