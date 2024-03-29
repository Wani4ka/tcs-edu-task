package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.ChatRepository;

import javax.sql.DataSource;
import java.util.List;

public class JdbcChatRepository extends JdbcRepository implements ChatRepository {

    private static final RowMapper<ChatEntity> MAPPER = new BeanPropertyRowMapper<>(ChatEntity.class);

    @Autowired
    public JdbcChatRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public void add(long id) {
        template.update("insert into chat (id) values (?) on conflict do nothing", id);
    }

    @Override
    public int remove(long id) {
        return template.update("delete from chat where id=?", id);
    }

    @Override
    public ChatEntity findById(long id) {
        return template.queryForObject("select id from chat where id=?", MAPPER, id);
    }

    @Override
    public List<ChatEntity> findAll() {
        return template.query("select id from chat", MAPPER);
    }
}
