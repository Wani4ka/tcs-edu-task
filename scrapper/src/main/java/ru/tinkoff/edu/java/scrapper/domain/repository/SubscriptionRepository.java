package ru.tinkoff.edu.java.scrapper.domain.repository;

import ru.tinkoff.edu.java.scrapper.domain.entity.Subscription;

import java.util.List;

public interface SubscriptionRepository {
    long add(long chatId, long linkId);
    boolean remove(long id);
    List<Subscription> findAll();
    List<Subscription> findByChat(long chatId);
    List<Subscription> findByLink(long linkId);
}
