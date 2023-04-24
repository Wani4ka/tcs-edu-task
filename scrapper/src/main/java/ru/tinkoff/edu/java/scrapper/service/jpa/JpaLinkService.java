package ru.tinkoff.edu.java.scrapper.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;

@RequiredArgsConstructor
public class JpaLinkService implements LinkService {

    private final JpaLinkRepository repository;

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