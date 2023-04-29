package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.jdbc.JdbcSubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.SubscriptionService;

import java.net.URI;
import java.util.Collection;

@RequiredArgsConstructor
public class JdbcSubscriptionService implements SubscriptionService {

    private final JdbcSubscriptionRepository repository;
    private final LinkService links;

    protected void subscribe(long chatId, LinkEntity link) {
        repository.add(chatId, link.getId());
    }

    @Override
    @Transactional
    public LinkEntity subscribe(long tgChatId, URI url) {
        var link = links.add(url);
        subscribe(tgChatId, link);
        return link;
    }

    @Transactional
    protected void unsubscribe(long chatId, LinkEntity link) {
        var sub = repository.findByData(chatId, link.getId());
        if (sub == null)
            return;
        repository.remove(sub.getId());
    }

    @Override
    @Transactional
    public LinkEntity unsubscribe(long tgChatId, URI url) {
        var link = links.findByUrl(url);
        unsubscribe(tgChatId, link);
        return link;
    }

    @Override
    @Transactional
    public Collection<LinkEntity> getSubscriptions(long tgChatId) {
        var subs = repository.findByChat(tgChatId);
        return subs.stream().map(sub -> links.findById(sub.getLinkId())).toList();
    }

    @Override
    public Collection<ChatEntity> getSubscribers(long linkId) {
        return repository.findByLink(linkId).stream().map(sub -> new ChatEntity(sub.getChatId())).toList();
    }
}
