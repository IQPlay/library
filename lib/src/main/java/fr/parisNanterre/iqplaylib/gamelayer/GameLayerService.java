package fr.parisnanterre.iqplaylib.gamelayer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * loadApiKey permet de charger la clé API depuis le fichier application.properties
 * le développeur qui utilisera la librairie et voudra utiliser l'api gamelayer, il devra renseigner sa clé API dans ce fichier
 */
public class GameLayerService {
    private static final String API_URL = "https://api.gamelayer.co"; // j"ai mis le lien de l'api
    private static String API_KEY;

    static {
        loadApiKey();
    }

    private static void loadApiKey() {
        Properties properties = new Properties();
        try (InputStream input = GameLayerService.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Fichier application.properties introuvable !");
            }
            properties.load(input);
            API_KEY = properties.getProperty("api.gamelayer");
            if (API_KEY == null) {
                throw new IOException("Clé api.gamelayer non trouvée dans application.properties !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
