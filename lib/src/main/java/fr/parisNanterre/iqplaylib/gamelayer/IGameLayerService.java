package fr.parisnanterre.iqplaylib.gamelayer;

import java.io.IOException;

/**
 * Interface définissant les méthodes d'accès à l'API GameLayer.
 */
interface IGameLayerService {

    // 📌 Gestion des joueurs
    String createPlayer(String name, String team, int points, int credits, String levelId, String levelName) throws IOException;

    String getPlayerById(String playerId) throws IOException;
    
    void deletePlayerById(String playerId) throws IOException; 
    
    String updatePlayer(String playerId, String fieldToUpdate, Object value) throws IOException; 
    
    String getPlayerLevels(String playerId) throws IOException; 

    // 📌 Trophées
    String getAchievementById(String id) throws IOException;
    
    String getAllAchievements() throws IOException;

    // 📌 Stats
    String getAnalytics() throws IOException;

    String getActivePlayers() throws IOException;

    // 📌 Events
    void completeEvent(String eventId) throws IOException;

    String getEventById(String id) throws IOException;

    String getAllEvents() throws IOException;

    // 📌 Classements (Leaderboards)
    String getLeaderboardById(String id) throws IOException;

    String getAllLeaderboards() throws IOException;

    String getLevelById(String id) throws IOException;

    String getAllLevels() throws IOException;

    // 📌 Missions
    String getMissionById(String id) throws IOException;

    String getAllMissions() throws IOException;
}
