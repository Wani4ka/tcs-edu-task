package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.entity.Subscription;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepository;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcSubscriptionRepository extends JdbcRepository implements SubscriptionRepository {

    private static final RowMapper<Subscription> MAPPER = new BeanPropertyRowMapper<>(Subscription.class);

    @Autowired
    public JdbcSubscriptionRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public void add(long chatId, long linkId) {
        jdbcTemplate.update("insert into subscription (link_id, chat_id) values (?, ?)", linkId, chatId);
    }

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update("delete from subscription where id=?", id) > 0;
    }

    @Override
    public List<Subscription> findAll() {
        return jdbcTemplate.query("select id, link_id, chat_id from subscription", MAPPER);
    }

    @Override
    public Subscription findById(long id) {
        return jdbcTemplate.queryForObject("select id, link_id, chat_id from subscription where id=?", MAPPER, id);
    }

    @Override
    public Subscription findByData(long chatId, long linkId) {
        return jdbcTemplate.queryForObject("select id, link_id, chat_id from subscription where chat_id=? and link_id=?", MAPPER, chatId, linkId);
    }

    @Override
    public List<Subscription> findByChat(long chatId) {
        return jdbcTemplate.query("select id, link_id, chat_id from subscription where chat_id=?", MAPPER, chatId);
    }

    @Override
    public List<Subscription> findByLink(long linkId) {
        return jdbcTemplate.query("select id, link_id, chat_id from subscription where link_id=?", MAPPER, linkId);
    }
}
