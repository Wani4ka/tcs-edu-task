package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.domain.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;

import java.net.URI;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class BotService {

    private final BotClient client;
    private final SubscriptionService subscriptions;

    public void sendUpdate(long linkId, URI url, OffsetDateTime lastEvent, String description) {
        client.sendUpdate(new LinkUpdateRequest(
                linkId,
                url,
                description + " at " + lastEvent,
                subscriptions.getSubscribers(linkId).stream().map(ChatEntity::getId).toList()
        ));
    }
}
