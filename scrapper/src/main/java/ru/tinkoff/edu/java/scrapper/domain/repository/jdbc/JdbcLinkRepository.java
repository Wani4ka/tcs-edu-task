package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

import javax.sql.DataSource;
import java.net.URI;
import java.util.List;

@Component
public class JdbcLinkRepository extends JdbcRepository implements LinkRepository {

    private static final RowMapper<Link> MAPPER = new BeanPropertyRowMapper<>(Link.class);

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
    public Link findById(long id) {
        return jdbcTemplate.queryForObject("select id, url from link where id=?", MAPPER, id);
    }

    @Override
    public Link findByUrl(URI url) {
        return jdbcTemplate.queryForObject("select id, url from link where url=?", new BeanPropertyRowMapper<>(Link.class), url.toString());
    }

    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query("select id, url from link", new BeanPropertyRowMapper<>(Link.class));
    }
}
