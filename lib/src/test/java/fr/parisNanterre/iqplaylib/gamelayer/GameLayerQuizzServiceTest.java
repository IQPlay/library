package fr.parisnanterre.iqplaylib.gamelayer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameLayerQuizzServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerQuizzService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        GameLayerQuizzService realService = new GameLayerQuizzService("dummy-key", "dummy-account");
        service = Mockito.spy(realService);

        when(service.getApiUrl()).thenReturn(mockWebServer.url("/").toString());
    }

    @Test
    void getQuizzById_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"id\": \"quizz123\"}"));

        HttpResponse response = service.getQuizzById("quizz123");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getQuizzes_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[{\"id\": \"quizz123\"}, {\"id\": \"quizz456\"}]"));

        HttpResponse response = service.getQuizzes();
        assertEquals(200, response.statusCode());
    }

    @Test
    void getQuizzesByPlayer_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[{\"id\": \"quizz789\"}]"));

        HttpResponse response = service.getQuizzesByPlayer("test-player");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getResultOfQuizz_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"result\": \"success\"}"));

        HttpResponse response = service.getResultOfQuizz("quizz123");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getResultOfQuizzByPlayer_Test() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"result\": \"success\", \"player\": \"test-player\"}"));

        HttpResponse response = service.getResultOfQuizzByPlayer("quizz123", "test-player");
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}