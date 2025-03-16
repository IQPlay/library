package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerLevelService {
    void getLevelById(String levelId, String account) throws IOException, InterruptedException;
    void getAllLevels(String account) throws IOException, InterruptedException;
}
