package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.domain.entity.Link;

import java.net.URI;
import java.util.Collection;

public interface SubscriptionService {
    Link subscribe(long tgChatId, URI url);
    Link unsubscribe(long tgChatId, URI url);
    Collection<Link> getSubscriptions(long tgChatId);
}
