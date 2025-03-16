package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

// Erreur 500 côté serveur de GameLayer
public interface IGameLayerStatService {
    String getAnalytics() throws IOException;

    String getActivePlayers() throws IOException;
}
