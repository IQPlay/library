package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerAchievementService {

    HttpResponse getAchievementById(String achievementId) throws IOException, InterruptedException;

    HttpResponse getAllAchievements() throws IOException, InterruptedException;
}
