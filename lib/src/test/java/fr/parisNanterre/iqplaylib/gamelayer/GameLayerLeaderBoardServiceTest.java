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

class GameLayerLeaderBoardServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerLeaderBoardService leaderBoardService;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        GameLayerLeaderBoardService realService = new GameLayerLeaderBoardService("dummy-key", "dummy-account");
        leaderBoardService = Mockito.spy(realService);

        when(leaderBoardService.getApiUrl()).thenReturn(mockWebServer.url("/").toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetLeaderboardById() throws Exception {
        String mockResponse = "{\"id\":\"123\", \"name\":\"Top Players\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = leaderBoardService.getLeaderboardById("123");

        assertEquals(200, response.statusCode());
        assertEquals(mockResponse, response.body().toString());
    }

    @Test
    void testGetAllLeaderboards() throws Exception {
        String mockResponse = "[{\"id\":\"123\", \"name\":\"Top Players\"}, {\"id\":\"124\", \"name\":\"Weekly Winners\"}]";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = leaderBoardService.getAllLeaderboards();

        assertEquals(200, response.statusCode());
        assertEquals(mockResponse, response.body().toString());
    }
}