package fr.parisnanterre.iqplaylib.gamelayer;

import fr.parisnanterre.iqplaylib.gamelayer.api.IGameLayerQuizzService;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GameLayerQuizzService extends GameLayerService implements IGameLayerQuizzService {
    @Override
    public HttpResponse getQuizzById(String quizz, String account) {
        return null;
    }

    @Override
    public HttpResponse getQuizzes(String account) {
        return null;
    }

    @Override
    public HttpResponse getResultOfQuizz(String quizz, String account) {
        return null;
    }

    @Override
    public HttpResponse startAQuizz(String quizz, String account, String player) {
        return null;
    }

    @Override
    public HttpResponse completeQuizz(String quizz, String account, String player, ArrayList<String> answers) {
        return null;
    }
}
