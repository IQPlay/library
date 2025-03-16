package fr.parisnanterre.iqplaylib.gamelayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Properties;

@Service
public abstract class GameLayerService implements IGameLayerService {

    protected static final Logger logger = LoggerFactory.getLogger(GameLayerService.class);
    protected static String API_URL = "https://api.gamelayer.co";
    protected static String API_KEY;
    protected static String ACCOUNT_ID;
    protected final ObjectMapper objectMapper = new ObjectMapper();
    protected HttpClient httpClient;

    protected GameLayerService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    protected GameLayerService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @PostConstruct
    public void init() {
        loadApiCredential();
    }

    public static void loadApiCredential() {
        try (InputStream input = GameLayerService.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Fichier application.properties introuvable !");
            }
            Properties properties = new Properties();
            properties.load(input);
            API_KEY = properties.getProperty("api.gamelayer.key");
            ACCOUNT_ID = properties.getProperty("api.gamelayer.accountId");
            if ((API_KEY == null || API_KEY.isEmpty()) || (ACCOUNT_ID == null || ACCOUNT_ID.isEmpty())) {
                throw new IOException("Clé API GameLayer ou id du compte non trouvée !");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    protected void logInformation(String message) {
        logger.info(message);
    }
}
