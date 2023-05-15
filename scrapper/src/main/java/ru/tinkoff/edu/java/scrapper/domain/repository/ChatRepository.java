package ru.tinkoff.edu.java.scrapper.domain.repository;

import ru.tinkoff.edu.java.scrapper.domain.entity.ChatEntity;

import java.util.List;

public interface ChatRepository {
    void add(long id);

    int remove(long id);

    ChatEntity findById(long id);

    List<ChatEntity> findAll();
}
