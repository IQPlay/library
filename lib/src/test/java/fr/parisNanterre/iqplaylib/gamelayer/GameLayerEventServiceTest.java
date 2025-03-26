package fr.parisnanterre.iqplaylib.gamelayer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GameLayerEventServiceTest {

    private MockWebServer mockWebServer;
    private GameLayerEventService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        GameLayerEventService realService = new GameLayerEventService("dummy-key", "dummy-account");
        service = Mockito.spy(realService);

        when(service.getApiUrl()).thenReturn(mockWebServer.url("/").toString());
    }

    @Test
    void completeEvent_Success_Test() throws Exception {
        String successBody = "{\"status\": \"completed\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(successBody)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.completeEvent("event123", "test-player");
        assertEquals(200, response.statusCode());
    }

    @Test
    void completeEvent_Failure_Test() throws Exception {
        String errorBody = "{\"error\": \"Bad Request\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(errorBody)
                .addHeader("Content-Type", "application/json"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.completeEvent("event123", "test-player");
        });
        assertTrue(exception.getMessage().contains("400"));
    }

    @Test
    void getEventById_Test() throws Exception {
        String responseBody = "{\"id\": \"event123\", \"name\": \"Event Test\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getEventById("event123");
        assertEquals(200, response.statusCode());
    }

    @Test
    void getAllEvents_Test() throws Exception {
        String responseBody = "[{\"id\": \"event123\"}, {\"id\": \"event456\"}]";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        HttpResponse response = service.getAllEvents();
        assertEquals(200, response.statusCode());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}