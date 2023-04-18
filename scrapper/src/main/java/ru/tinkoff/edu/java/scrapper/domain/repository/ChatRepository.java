package ru.tinkoff.edu.java.scrapper.domain.repository;

import ru.tinkoff.edu.java.scrapper.domain.entity.Chat;

import java.util.List;

public interface ChatRepository {
    long add(long id);
    boolean remove(long id);
    List<Chat> findAll();
}
