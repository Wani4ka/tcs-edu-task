package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.SubscriptionEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepository;

import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Subscription.*;

@Repository
@RequiredArgsConstructor
public class JooqSubscriptionRepository implements SubscriptionRepository {
    private final DSLContext context;

    @Override
    public void add(long chatId, long linkId) {
        context.insertInto(SUBSCRIPTION)
                .set(SUBSCRIPTION.CHAT_ID, chatId)
                .set(SUBSCRIPTION.LINK_ID, linkId)
                .onConflictDoNothing()
                .execute();
    }

    @Override
    public boolean remove(long id) {
        return context.deleteFrom(SUBSCRIPTION)
                .where(SUBSCRIPTION.ID.eq(id))
                .execute() > 0;
    }

    @Override
    public List<SubscriptionEntity> findAll() {
        return context.select(SUBSCRIPTION.fields())
                .from(SUBSCRIPTION)
                .fetchInto(SubscriptionEntity.class);
    }

    @Override
    public SubscriptionEntity findById(long id) {
        return context.select(SUBSCRIPTION.fields())
                .from(SUBSCRIPTION)
                .where(SUBSCRIPTION.ID.eq(id))
                .fetchOneInto(SubscriptionEntity.class);
    }

    @Override
    public SubscriptionEntity findByData(long chatId, long linkId) {
        return context.select(SUBSCRIPTION.fields())
                .from(SUBSCRIPTION)
                .where(SUBSCRIPTION.CHAT_ID.eq(chatId))
                .and(SUBSCRIPTION.LINK_ID.eq(linkId))
                .fetchOneInto(SubscriptionEntity.class);
    }

    @Override
    public List<SubscriptionEntity> findByChat(long chatId) {
        return context.select(SUBSCRIPTION.fields())
                .from(SUBSCRIPTION)
                .where(SUBSCRIPTION.CHAT_ID.eq(chatId))
                .fetchInto(SubscriptionEntity.class);
    }

    @Override
    public List<SubscriptionEntity> findByLink(long linkId) {
        return context.select(SUBSCRIPTION.fields())
                .from(SUBSCRIPTION)
                .where(SUBSCRIPTION.LINK_ID.eq(linkId))
                .fetchInto(SubscriptionEntity.class);
    }
}
