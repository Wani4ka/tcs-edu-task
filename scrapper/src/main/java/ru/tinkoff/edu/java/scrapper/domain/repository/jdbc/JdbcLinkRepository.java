package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

import javax.sql.DataSource;
import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public class JdbcLinkRepository extends JdbcRepository implements LinkRepository {

    private static final RowMapper<LinkEntity> MAPPER = new BeanPropertyRowMapper<>(LinkEntity.class);

    @Autowired
    public JdbcLinkRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public void add(URI url) {
        template.update("insert into link (url) values (?) on conflict do nothing", url.toString());
    }

    @Override
    public int remove(long id) {
        return template.update("delete from link where id=?", id);
    }

    @Override
    public LinkEntity findById(long id) {
        return template.queryForObject("select id, url, last_check, last_event from link where id=?", MAPPER, id);
    }

    @Override
    public LinkEntity findByUrl(URI url) {
        return template.queryForObject("select id, url, last_check, last_event from link where url=?",
            MAPPER, url.toString());
    }

    @Override
    public List<LinkEntity> findAll() {
        return template.query("select id, url, last_check, last_event from link", MAPPER);
    }

    @Override
    public Collection<LinkEntity> peekOld(Duration maxAge) {
        return template.query("update link set last_check=now() where last_check < ? " +
                "returning id, url, last_check, last_event",
            MAPPER, OffsetDateTime.now().minus(maxAge));
    }

    @Override
    public void updateLastEvent(long id, OffsetDateTime lastEvent) {
        template.update("update link set last_event=? where id=?", lastEvent, id);
    }
}
