package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.net.http.HttpResponse;

public interface IGameLayerStreakService {
    HttpResponse getStreakById(String streakId) throws Exception, InterruptedException;
    HttpResponse getAllStreaks() throws Exception, InterruptedException;
}
