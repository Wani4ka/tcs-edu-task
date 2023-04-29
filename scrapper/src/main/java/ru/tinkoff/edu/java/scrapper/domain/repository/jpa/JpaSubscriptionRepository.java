package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.scrapper.domain.entity.SubscriptionEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepository;

import java.util.List;

public interface JpaSubscriptionRepository extends JpaRepository<SubscriptionEntity, Long>, SubscriptionRepository {

    @Override
    @Modifying
    @Query(value = "insert into subscription (link_id, chat_id) values (:linkId, :chatId)", nativeQuery = true)
    void add(@Param("chatId") long chatId, @Param("linkId") long linkId);

    @Override
    @Modifying
    @Query("delete from subscription s where s.id=:id")
    int remove(@Param("id") long id);

    @Override
    @Query("select s from subscription s where s.chatId=:chatId and s.linkId=:linkId")
    SubscriptionEntity findByData(@Param("chatId") long chatId, @Param("linkId") long linkId);

    @Override
    @Query("select s from subscription s where s.chatId=:chatId")
    List<SubscriptionEntity> findByChat(@Param("chatId") long chatId);

    @Override
    @Query("select s from subscription s where s.linkId=:linkId")
    List<SubscriptionEntity> findByLink(@Param("linkId") long linkId);
}
