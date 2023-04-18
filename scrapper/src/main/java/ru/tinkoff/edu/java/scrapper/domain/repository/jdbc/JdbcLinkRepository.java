package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

import javax.sql.DataSource;
import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Component
public class JdbcLinkRepository extends JdbcRepository implements LinkRepository {

    private static final RowMapper<LinkEntity> MAPPER = new BeanPropertyRowMapper<>(LinkEntity.class);

    @Autowired
    public JdbcLinkRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public void add(URI url) {
        jdbcTemplate.update("insert into link (url) values (?)", url.toString());
    }

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update("delete from link where id=?", id) > 0;
    }

    @Override
    public LinkEntity findById(long id) {
        return jdbcTemplate.queryForObject("select id, url, last_check, last_event from link where id=?", MAPPER, id);
    }

    @Override
    public LinkEntity findByUrl(URI url) {
        return jdbcTemplate.queryForObject("select id, url, last_check, last_event from link where url=?", MAPPER, url.toString());
    }

    @Override
    public List<LinkEntity> findAll() {
        return jdbcTemplate.query("select id, url, last_check, last_event from link", MAPPER);
    }

    @Override
    public Collection<LinkEntity> peekOld(Duration maxAge) {
        return jdbcTemplate.query("update link set last_check=now() where last_check < ? returning id, url, last_check, last_event", MAPPER, OffsetDateTime.now().minus(maxAge));
    }

    @Override
    public void updateLastEvent(long id, OffsetDateTime lastEvent) {
        jdbcTemplate.update("update link set last_event=? where id=?", lastEvent, id);
    }
}
