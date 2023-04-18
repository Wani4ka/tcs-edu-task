package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.*;

@Repository
@RequiredArgsConstructor
public class JooqLinkRepository implements LinkRepository {
    private final DSLContext context;

    @Override
    public void add(URI url) {
        context.insertInto(LINK)
                .set(LINK.URL, url.toString())
                .onConflictDoNothing()
                .execute();
    }

    @Override
    public boolean remove(long id) {
        return context.deleteFrom(LINK)
                .where(LINK.ID.eq(id))
                .execute() > 0;
    }

    @Override
    public LinkEntity findById(long id) {
        return context.select(LINK.fields())
                .from(LINK)
                .where(LINK.ID.eq(id))
                .fetchOneInto(LinkEntity.class);
    }

    @Override
    public LinkEntity findByUrl(URI url) {
        return context.select(LINK.fields())
                .from(LINK)
                .where(LINK.URL.eq(url.toString()))
                .fetchOneInto(LinkEntity.class);
    }

    @Override
    public List<LinkEntity> findAll() {
        return context.select(LINK.fields())
                .from(LINK)
                .fetchInto(LinkEntity.class);
    }

    @Override
    public Collection<LinkEntity> peekOld(Duration maxAge) {
        return context.update(LINK)
                .set(LINK.LAST_CHECK, OffsetDateTime.now())
                .where(LINK.LAST_CHECK.lt(OffsetDateTime.now().minus(maxAge)))
                .returning(LINK.fields())
                .fetchInto(LinkEntity.class);
    }

    @Override
    public void updateLastEvent(long id, OffsetDateTime lastEvent) {
        context.update(LINK)
                .set(LINK.LAST_EVENT, lastEvent)
                .where(LINK.ID.eq(id))
                .execute();
    }
}
