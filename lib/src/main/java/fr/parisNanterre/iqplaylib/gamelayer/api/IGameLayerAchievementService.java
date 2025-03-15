package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerAchievementService {
    String getAchievementById(String id) throws IOException;

    String getAllAchievements() throws IOException;
}
