package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;

import java.net.URI;

public interface LinkService {
    LinkEntity add(URI url);

    LinkEntity findById(long id);

    LinkEntity findByUrl(URI url);

    LinkEntity remove(URI url);
}
