package fr.parisnanterre.iqplaylib.gamelayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.http.HttpClient;
import java.time.Duration;

public abstract class GameLayerService implements IGameLayerService {

    private static final Logger logger = LoggerFactory.getLogger(GameLayerService.class);
    private static final String API_URL = "https://api.gamelayer.co";

    private String apiKey;
    private String accountId;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient;

    @Autowired
    public GameLayerService(@Value("${api.gamelayer.key}") String apiKey,
                            @Value("${api.gamelayer.accountId}") String accountId) {
        this.apiKey = apiKey;
        this.accountId = accountId;
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    }

    public String getApiUrl() {
        return API_URL;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getAccountId() {
        return accountId;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected void logInformation(String message) {
        logger.info(message);
    }
}
