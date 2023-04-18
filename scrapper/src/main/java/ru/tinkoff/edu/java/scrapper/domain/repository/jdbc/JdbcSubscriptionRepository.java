package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.entity.Subscription;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcSubscriptionRepository extends JdbcRepository implements SubscriptionRepository {

    @Autowired
    public JdbcSubscriptionRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public long add(long chatId, long linkId) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement("INSERT INTO subscription (link_id, chat_id) VALUES (?, ?)", new String[]{"id"});
            ps.setLong(1, linkId);
            ps.setLong(2, chatId);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update("DELETE FROM subscription WHERE id=?", id) > 0;
    }

    @Override
    public List<Subscription> findAll() {
        return jdbcTemplate.query("SELECT id, link_id, chat_id FROM subscription", new BeanPropertyRowMapper<>(Subscription.class));
    }

    @Override
    public List<Subscription> findByChat(long chatId) {
        return jdbcTemplate.query("SELECT id, link_id, chat_id FROM subscription WHERE chat_id=?", new BeanPropertyRowMapper<>(Subscription.class), chatId);
    }

    @Override
    public List<Subscription> findByLink(long linkId) {
        return jdbcTemplate.query("SELECT id, link_id, chat_id FROM subscription WHERE link_id=?", new BeanPropertyRowMapper<>(Subscription.class), linkId);
    }
}
