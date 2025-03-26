package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public interface IGameLayerQuizzService {
    HttpResponse getQuizzById(String quizz) throws IOException, InterruptedException;
    HttpResponse getQuizzes() throws IOException, InterruptedException;
    HttpResponse getQuizzesByPlayer(String player) throws IOException, InterruptedException;
    HttpResponse getResultOfQuizz(String quizz) throws IOException, InterruptedException;
    HttpResponse getResultOfQuizzByPlayer(String quizz, String player) throws IOException, InterruptedException;
    HttpResponse startAQuizz(String quizz, String player);
    HttpResponse completeQuizz(String quizz, String player, ArrayList<String> answers);
}
