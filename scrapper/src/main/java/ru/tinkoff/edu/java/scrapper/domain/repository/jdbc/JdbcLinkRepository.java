package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

import javax.sql.DataSource;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcLinkRepository extends JdbcRepository implements LinkRepository {

    @Autowired
    public JdbcLinkRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public long add(URI url) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement("INSERT INTO link (url) VALUES (?)", new String[]{"id"});
            ps.setString(1, url.toString());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update("DELETE FROM link WHERE id=?", id) > 0;
    }

    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query("SELECT id, url FROM link", new BeanPropertyRowMapper<>(Link.class));
    }
}
