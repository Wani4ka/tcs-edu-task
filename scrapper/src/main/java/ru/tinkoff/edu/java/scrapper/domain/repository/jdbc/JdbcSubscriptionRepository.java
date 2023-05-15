package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.entity.SubscriptionEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepository;

import javax.sql.DataSource;
import java.util.List;

public class JdbcSubscriptionRepository extends JdbcRepository implements SubscriptionRepository {

    private static final RowMapper<SubscriptionEntity> MAPPER = new BeanPropertyRowMapper<>(SubscriptionEntity.class);

    @Autowired
    public JdbcSubscriptionRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public void add(long chatId, long linkId) {
        template.update("insert into subscription (link_id, chat_id) values (?, ?) on conflict do nothing",
            linkId, chatId);
    }

    @Override
    public int remove(long id) {
        return template.update("delete from subscription where id=?", id);
    }

    @Override
    public List<SubscriptionEntity> findAll() {
        return template.query("select id, link_id, chat_id from subscription", MAPPER);
    }

    @Override
    public SubscriptionEntity findById(long id) {
        return template.queryForObject("select id, link_id, chat_id from subscription where id=?", MAPPER, id);
    }

    @Override
    public SubscriptionEntity findByData(long chatId, long linkId) {
        return template.queryForObject("select id, link_id, chat_id from subscription where chat_id=? and link_id=?",
            MAPPER, chatId, linkId);
    }

    @Override
    public List<SubscriptionEntity> findByChat(long chatId) {
        return template.query("select id, link_id, chat_id from subscription where chat_id=?", MAPPER, chatId);
    }

    @Override
    public List<SubscriptionEntity> findByLink(long linkId) {
        return template.query("select id, link_id, chat_id from subscription where link_id=?", MAPPER, linkId);
    }
}
