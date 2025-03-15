package fr.parisnanterre.iqplaylib.gamelayer.api;

import java.io.IOException;

public interface IGameLayerEventService {
    void completeEvent(String eventId) throws IOException;

    String getEventById(String id) throws IOException;

    String getAllEvents() throws IOException;
}
