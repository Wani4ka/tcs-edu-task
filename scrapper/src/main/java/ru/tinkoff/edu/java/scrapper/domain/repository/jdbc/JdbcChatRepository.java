package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.Chat;
import ru.tinkoff.edu.java.scrapper.domain.repository.ChatRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcChatRepository extends JdbcRepository implements ChatRepository {

    private static final RowMapper<Chat> MAPPER = new BeanPropertyRowMapper<>(Chat.class);

    @Autowired
    public JdbcChatRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public void add(long id) {
        jdbcTemplate.update("insert into chat (id) values (?)", id);
    }

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update("delete from chat where id=?", id) > 0;
    }

    @Override
    public Chat findById(long id) {
        return jdbcTemplate.queryForObject("select id from chat where id=?", MAPPER, id);
    }

    @Override
    public List<Chat> findAll() {
        return jdbcTemplate.query("select id from chat", MAPPER);
    }
}
