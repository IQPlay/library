package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerMissionService {
    void getMissionById(String missionId, String account) throws IOException, InterruptedException;
    void getAllMissions(String account) throws IOException, InterruptedException;
}
