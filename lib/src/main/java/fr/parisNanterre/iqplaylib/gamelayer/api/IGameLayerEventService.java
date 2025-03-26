package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IGameLayerEventService {
    HttpResponse completeEvent(String eventId, String player) throws IOException, InterruptedException;

    HttpResponse getEventById(String EventId) throws IOException, InterruptedException;

    HttpResponse getAllEvents() throws IOException, InterruptedException;
}
