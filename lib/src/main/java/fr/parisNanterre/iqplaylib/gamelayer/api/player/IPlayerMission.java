package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.io.IOException;

public interface IPlayerMission {
    void getMissionByPlayerId(String player, String account) throws IOException, InterruptedException;
    void getMissionByPlayerIdAndMissionId(String player, String account, String missionId) throws IOException, InterruptedException;
}
