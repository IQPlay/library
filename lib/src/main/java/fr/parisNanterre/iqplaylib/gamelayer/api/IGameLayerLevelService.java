package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerLevelService {
    HttpResponse getLevelById(String levelId) throws IOException, InterruptedException;
    HttpResponse getAllLevels() throws IOException, InterruptedException;
}
