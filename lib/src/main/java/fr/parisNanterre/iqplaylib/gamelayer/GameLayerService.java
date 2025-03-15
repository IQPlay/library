package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

@Service
public abstract class GameLayerService implements IGameLayerService {

    private static final Logger logger = LoggerFactory.getLogger(GameLayerService.class);

    protected static final String API_URL = "https://api.gamelayer.co";
    protected static String API_KEY;
    protected static String ACCOUNT_ID;

    static {
        loadApiKey();
    }

    private static void loadApiKey() {
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

}
