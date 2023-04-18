package ru.tinkoff.edu.java.scrapper.domain.repository;

import ru.tinkoff.edu.java.scrapper.domain.entity.Subscription;

import java.util.List;

public interface SubscriptionRepository {
    void add(long chatId, long linkId);
    boolean remove(long id);
    List<Subscription> findAll();
    Subscription findById(long id);
    Subscription findByData(long chatId, long linkId);
    List<Subscription> findByChat(long chatId);
    List<Subscription> findByLink(long linkId);
}
