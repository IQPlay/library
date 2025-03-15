package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerStatService {
    String getAnalytics() throws IOException;

    String getActivePlayers() throws IOException;
}
