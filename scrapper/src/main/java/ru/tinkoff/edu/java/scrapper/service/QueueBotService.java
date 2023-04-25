package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.domain.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;

import java.net.URI;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
public class QueueBotService implements BotService {

    private final ScrapperQueueProducer queue;
    private final SubscriptionService subscriptions;

    @Override
    public void sendUpdate(long linkId, URI url, OffsetDateTime lastEvent, String description) {
        queue.send(new LinkUpdateRequest(
                linkId,
                url,
                description + " at " + lastEvent,
                subscriptions.getSubscribers(linkId).stream().map(ChatEntity::getId).toList()
        ));
    }
}
