package fr.parisnanterre.iqplaylib.gamelayer.api.player;

public interface IPlayerRanking {
    void getRankingByPlayer(String player, String account) throws Exception, InterruptedException;
}
