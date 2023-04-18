package ru.tinkoff.edu.java.scrapper.domain.repository;

import ru.tinkoff.edu.java.scrapper.domain.entity.Link;

import java.net.URI;
import java.util.List;

public interface LinkRepository {
    void add(URI url);
    boolean remove(long id);
    Link findById(long id);
    Link findByUrl(URI url);
    List<Link> findAll();
}
