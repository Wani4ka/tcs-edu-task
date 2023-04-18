package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.SubscriptionService;

import java.net.URI;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class JdbcSubscriptionService implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final LinkService links;

    protected void subscribe(long chatId, Link link) {
        repository.add(chatId, link.getId());
    }

    @Override
    @Transactional
    public Link subscribe(long tgChatId, URI url) {
        var link = links.add(url);
        subscribe(tgChatId, link);
        return link;
    }

    @Transactional
    protected void unsubscribe(long chatId, Link link) {
        var sub = repository.findByData(chatId, link.getId());
        if (sub == null)
            return;
        repository.remove(sub.getId());
    }

    @Override
    public Link unsubscribe(long tgChatId, URI url) {
        var link = links.findByUrl(url);
        unsubscribe(tgChatId, link);
        return link;
    }

    @Override
    @Transactional
    public Collection<Link> getSubscriptions(long tgChatId) {
        var subs = repository.findByChat(tgChatId);
        return subs.stream().map(sub -> links.findById(sub.getLinkId())).toList();
    }
}
