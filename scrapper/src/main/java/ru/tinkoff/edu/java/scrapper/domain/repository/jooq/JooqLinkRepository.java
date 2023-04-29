package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK;

@RequiredArgsConstructor
public class JooqLinkRepository implements LinkRepository {
    private static final RecordMapper<Record, LinkEntity> MAPPER = record -> record == null ? null : new LinkEntity(
            record.get("id", Long.class),
            URI.create(record.get("url", String.class)),
            record.get("last_check", OffsetDateTime.class),
            record.get("last_event", OffsetDateTime.class)
    );

    private final DSLContext context;

    @Override
    public void add(URI url) {
        context.insertInto(LINK)
                .set(LINK.URL, url.toString())
                .onConflictDoNothing()
                .execute();
    }

    @Override
    public int remove(long id) {
        return context.deleteFrom(LINK)
                .where(LINK.ID.eq(id))
                .execute();
    }

    @Override
    public LinkEntity findById(long id) {
        return context.select(LINK.fields())
                .from(LINK)
                .where(LINK.ID.eq(id))
                .fetchOne(MAPPER);
    }

    @Override
    public LinkEntity findByUrl(URI url) {
        return context.select(LINK.fields())
                .from(LINK)
                .where(LINK.URL.eq(url.toString()))
                .fetchOne(MAPPER);
    }

    @Override
    public List<LinkEntity> findAll() {
        return context.select(LINK.fields())
                .from(LINK)
                .fetch(MAPPER);
    }

    @Override
    public Collection<LinkEntity> peekOld(Duration maxAge) {
        return context.update(LINK)
                .set(LINK.LAST_CHECK, OffsetDateTime.now())
                .where(LINK.LAST_CHECK.lt(OffsetDateTime.now().minus(maxAge)))
                .returning(LINK.fields())
                .fetch(MAPPER);
    }

    @Override
    public void updateLastEvent(long id, OffsetDateTime lastEvent) {
        context.update(LINK)
                .set(LINK.LAST_EVENT, lastEvent)
                .where(LINK.ID.eq(id))
                .execute();
    }
}
