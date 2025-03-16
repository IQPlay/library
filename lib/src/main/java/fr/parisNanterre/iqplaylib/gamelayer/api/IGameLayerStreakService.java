package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.net.http.HttpResponse;

public interface IGameLayerStreakService {
    HttpResponse getStreakById(String streakId, String account) throws Exception, InterruptedException;
    HttpResponse getAllStreaks(String account) throws Exception, InterruptedException;
}
