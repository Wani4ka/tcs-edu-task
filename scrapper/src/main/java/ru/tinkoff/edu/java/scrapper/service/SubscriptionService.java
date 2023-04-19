package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.domain.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;

import java.net.URI;
import java.util.Collection;

public interface SubscriptionService {
    LinkEntity subscribe(long tgChatId, URI url);
    LinkEntity unsubscribe(long tgChatId, URI url);
    Collection<LinkEntity> getSubscriptions(long tgChatId);
    Collection<ChatEntity> getSubscribers(long linkId);
}
