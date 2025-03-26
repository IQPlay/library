package fr.parisnanterre.iqplaylib.gamelayer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.http.HttpClient;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GameLayerServiceTest {

    private GameLayerService service;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private static class TestableGameLayerService extends GameLayerService {
        public TestableGameLayerService(String apiKey, String accountId) {
            super(apiKey, accountId);
        }
    }

    @BeforeEach
    void setUp() {
        service = Mockito.spy(new TestableGameLayerService("dummy-key", "dummy-account"));
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testLogInformation() {
        assertDoesNotThrow(() -> service.logInformation("Test message"));
    }

    @Test
    void testHttpClientConfiguration() {
        HttpClient client = service.getHttpClient();
        assertEquals(Duration.ofSeconds(10), client.connectTimeout().orElseThrow());
    }

    @Test
    void testGetters() {
        assertEquals("dummy-key", service.getApiKey());
        assertEquals("dummy-account", service.getAccountId());
        assertNotNull(service.getObjectMapper());
    }
}