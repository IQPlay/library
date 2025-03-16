package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerEventService {
    void completeEvent(String eventId, String player, String account) throws IOException, InterruptedException;

    void getEventById(String EventId, String account) throws IOException, InterruptedException;

    void getAllEvents(String account) throws IOException, InterruptedException;
}
