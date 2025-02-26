package fr.parisnanterre.iqplaylib.gamelayer;

import java.io.IOException;

/**
 * Quelques idées de méthodes à implémenter pour l'api GameLayer.
 */
interface IGameLayerService {

    // Trophées
    String getAchievementById(String id) throws IOException;

    String getAllAchievements() throws IOException;

    // Stats
    String getAnalytics() throws IOException;

    String getActivePlayers() throws IOException;

    // Events
    void completeEvent(String eventId) throws IOException;

    String getEventById(String id) throws IOException;

    String getAllEvents() throws IOException;

    // Leaderboards
    String getLeaderboardById(String id) throws IOException ;

    String getAllLeaderboards() throws IOException;

    String getLevelById(String id) throws IOException;

    String getAllLevels() throws IOException;

    // Missions
    String getMissionById(String id) throws IOException;

    String getAllMissions() throws IOException;

}
