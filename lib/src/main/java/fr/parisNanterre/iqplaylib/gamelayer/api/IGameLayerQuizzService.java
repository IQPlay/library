package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public interface IGameLayerQuizzService {
    HttpResponse getQuizzById(String quizz, String account);
    HttpResponse getQuizzes(String account);
    HttpResponse getResultOfQuizz(String quizz, String account);
    HttpResponse startAQuizz(String quizz, String account, String player);
    HttpResponse completeQuizz(String quizz, String account, String player, ArrayList<String> answers);
}
