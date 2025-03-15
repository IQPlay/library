package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerMissionService {
    String getMissionById(String id) throws IOException;

    String getAllMissions() throws IOException;
}
