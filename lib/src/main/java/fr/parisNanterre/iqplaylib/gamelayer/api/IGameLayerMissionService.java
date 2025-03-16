package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerMissionService {
    HttpResponse getMissionById(String missionId, String account) throws IOException, InterruptedException;
    HttpResponse getAllMissions(String account) throws IOException, InterruptedException;
}
