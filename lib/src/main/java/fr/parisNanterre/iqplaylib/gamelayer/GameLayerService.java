package fr.parisnanterre.iqplaylib.gamelayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.parisnanterre.iqplaylib.gamelayer.dto.PlayerDTO;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class GameLayerService{

    private static final String API_URL = "https://api.gamelayer.co"; // URL de base de l'API
    private static String API_KEY;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

    // Chargement de la clé API au démarrage
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
            API_KEY = properties.getProperty("api.gamelayer");
            if (API_KEY == null || API_KEY.isEmpty()) {
                throw new IOException("Clé API GameLayer non trouvée !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un joueur sur GameLayer.
     */
    
    public String createPlayer(String name, String team, int points, int credits, String levelId, String levelName) throws IOException {
        String requestUrl = API_URL + "/api/v0/players";
        HttpURLConnection connection = (HttpURLConnection) new URL(requestUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Construction du JSON
        PlayerDTO playerDTO = new PlayerDTO(name, team, points, credits, levelId, levelName);
        String jsonPayload = objectMapper.writeValueAsString(playerDTO);

        // Envoi de la requête
        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonPayload.getBytes("utf-8"));
        }

        return handleResponse(connection);
    }

    /**
     * Récupère les informations d'un joueur par son ID.
     */
    
    public String getPlayerById(String playerId) throws IOException {
        String requestUrl = API_URL + "/api/v0/players/" + playerId;
        HttpURLConnection connection = (HttpURLConnection) new URL(requestUrl).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        return handleResponse(connection);
    }

    /**
     * Supprime un joueur par son ID.
     */
    
    public void deletePlayerById(String playerId) throws IOException {
        String requestUrl = API_URL + "/api/v0/players/" + playerId;
        HttpURLConnection connection = (HttpURLConnection) new URL(requestUrl).openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        if (connection.getResponseCode() != 204) { // 204 = No Content (Succès)
            throw new IOException("Erreur lors de la suppression du joueur: " + connection.getResponseCode());
        }
    }

    /**
     * Met à jour un joueur sur un champ spécifique.
     */
    
    public String updatePlayer(String playerId, String fieldToUpdate, Object value) throws IOException {
        String requestUrl = API_URL + "/api/v0/players/" + playerId;
        HttpURLConnection connection = (HttpURLConnection) new URL(requestUrl).openConnection();
        connection.setRequestMethod("PATCH");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Création du JSON avec le champ mis à jour
        Map<String, Object> updatePayload = new HashMap<>();
        updatePayload.put(fieldToUpdate, value);
        String jsonPayload = objectMapper.writeValueAsString(updatePayload);

        // Envoi de la requête
        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonPayload.getBytes("utf-8"));
        }

        return handleResponse(connection);
    }

    /**
     * Récupère les niveaux complétés d'un joueur.
     */
    
    public String getPlayerLevels(String playerId) throws IOException {
        String requestUrl = API_URL + "/api/v0/players/" + playerId + "/levels";
        HttpURLConnection connection = (HttpURLConnection) new URL(requestUrl).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        return handleResponse(connection);
    }

    /**
     * Gère la lecture des réponses API.
     */
    private String handleResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        if (responseCode >= 200 && responseCode < 300) {
            try (InputStream inputStream = connection.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            throw new IOException("Erreur API GameLayer - Code HTTP : " + responseCode);
        }
    }
}
