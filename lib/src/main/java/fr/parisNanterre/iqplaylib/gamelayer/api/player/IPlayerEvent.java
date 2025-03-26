package fr.parisnanterre.iqplaylib.gamelayer.api.player;

import java.net.http.HttpResponse;

public interface IPlayerEvent {
    HttpResponse getEventsByPlayer(String player) throws Exception, InterruptedException;
}
