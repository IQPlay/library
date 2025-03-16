package fr.parisnanterre.iqplaylib.gamelayer.api.player;

public interface IPlayerEvent {
    void getEventsByPlayer(String player, String account) throws Exception, InterruptedException;
}
