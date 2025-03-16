package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IPlayerMission {
    HttpResponse getMissionByPlayerId(String player, String account) throws IOException, InterruptedException;
    HttpResponse getMissionByPlayerIdAndMissionId(String player, String account, String missionId) throws IOException, InterruptedException;
}
