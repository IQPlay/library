package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerEventService {
    HttpResponse completeEvent(String eventId, String player, String account) throws IOException, InterruptedException;

    HttpResponse getEventById(String EventId, String account) throws IOException, InterruptedException;

    HttpResponse getAllEvents(String account) throws IOException, InterruptedException;
}
