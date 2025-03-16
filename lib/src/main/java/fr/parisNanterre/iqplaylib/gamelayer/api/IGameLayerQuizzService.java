package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public interface IGameLayerQuizzService {
    HttpResponse getQuizzById(String quizz, String account) throws IOException, InterruptedException;
    HttpResponse getQuizzes(String account) throws IOException, InterruptedException;
    HttpResponse getQuizzesByPlayer(String player, String account) throws IOException, InterruptedException;
    HttpResponse getResultOfQuizz(String quizz, String account) throws IOException, InterruptedException;
    HttpResponse getResultOfQuizzByPlayer(String quizz, String account, String player) throws IOException, InterruptedException;
    HttpResponse startAQuizz(String quizz, String account, String player);
    HttpResponse completeQuizz(String quizz, String account, String player, ArrayList<String> answers);
}
