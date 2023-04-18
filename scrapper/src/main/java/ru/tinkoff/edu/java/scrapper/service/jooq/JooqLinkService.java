package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;

@Service
@Primary
@RequiredArgsConstructor
public class JooqLinkService implements LinkService {

    private final JooqLinkRepository repository;

    @Override
    public LinkEntity add(URI url) {
        repository.add(url);
        return repository.findByUrl(url);
    }

    @Override
    public LinkEntity findById(long id) {
        return repository.findById(id);
    }

    @Override
    public LinkEntity findByUrl(URI url) {
        return repository.findByUrl(url);
    }

    @Override
    @Transactional
    public LinkEntity remove(URI url) {
        var link = findByUrl(url);
        if (link == null)
            return null;
        repository.remove(link.getId());
        return link;
    }
}