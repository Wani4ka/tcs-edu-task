package ru.tinkoff.edu.java.scrapper.service;

import java.net.URI;
import java.time.OffsetDateTime;

public interface BotService {
    void sendUpdate(long linkId, URI url, OffsetDateTime lastEvent, String description);
}
