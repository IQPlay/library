package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerAchievementService {

    void getAchievementById(String achievementId, String account) throws IOException, InterruptedException;

    void getAllAchievements(String account) throws IOException, InterruptedException;
}
