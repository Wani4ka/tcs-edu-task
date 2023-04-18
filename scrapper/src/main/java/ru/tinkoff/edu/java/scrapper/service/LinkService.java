package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.domain.entity.Link;

import java.net.URI;

public interface LinkService {
    Link add(URI url);
    Link findById(long id);
    Link findByUrl(URI url);
    Link remove(URI url);
}
