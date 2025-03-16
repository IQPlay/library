package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerLevelService {
    HttpResponse getLevelById(String levelId, String account) throws IOException, InterruptedException;
    HttpResponse getAllLevels(String account) throws IOException, InterruptedException;
}
