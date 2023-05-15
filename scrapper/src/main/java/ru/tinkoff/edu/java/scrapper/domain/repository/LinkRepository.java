package ru.tinkoff.edu.java.scrapper.domain.repository;

import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;

import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public interface LinkRepository {
    void add(URI url);

    int remove(long id);

    LinkEntity findById(long id);

    LinkEntity findByUrl(URI url);

    List<LinkEntity> findAll();

    Collection<LinkEntity> peekOld(Duration maxAge);

    void updateLastEvent(long id, OffsetDateTime lastEvent);
}
