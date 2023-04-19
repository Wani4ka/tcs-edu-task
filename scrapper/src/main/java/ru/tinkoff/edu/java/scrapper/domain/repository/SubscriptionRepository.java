package ru.tinkoff.edu.java.scrapper.domain.repository;

import ru.tinkoff.edu.java.scrapper.domain.entity.SubscriptionEntity;

import java.util.List;

public interface SubscriptionRepository {
    void add(long chatId, long linkId);
    boolean remove(long id);
    List<SubscriptionEntity> findAll();
    SubscriptionEntity findById(long id);
    SubscriptionEntity findByData(long chatId, long linkId);
    List<SubscriptionEntity> findByChat(long chatId);
    List<SubscriptionEntity> findByLink(long linkId);
}
