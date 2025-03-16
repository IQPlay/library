package fr.parisnanterre.iqplaylib.gamelayer.api;

public interface IGameLayerStreakService {
    void getStreakById(String streakId, String account) throws Exception, InterruptedException;
    void getAllStreaks(String account) throws Exception, InterruptedException;
}
