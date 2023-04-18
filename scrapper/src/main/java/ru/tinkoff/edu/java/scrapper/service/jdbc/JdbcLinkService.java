package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private final LinkRepository repository;

    @Override
    public Link add(URI url) {
        repository.add(url);
        return repository.findByUrl(url);
    }

    @Override
    public Link findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Link findByUrl(URI url) {
        return repository.findByUrl(url);
    }

    @Override
    @Transactional
    public Link remove(URI url) {
        var link = findByUrl(url);
        if (link == null)
            return null;
        repository.remove(link.getId());
        return link;
    }
}
