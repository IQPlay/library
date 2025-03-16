package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerAchievementService {

    HttpResponse getAchievementById(String achievementId, String account) throws IOException, InterruptedException;

    HttpResponse getAllAchievements(String account) throws IOException, InterruptedException;
}
