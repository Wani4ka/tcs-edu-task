package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.Chat;
import ru.tinkoff.edu.java.scrapper.domain.repository.ChatRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcChatRepository extends JdbcRepository implements ChatRepository {

    @Autowired
    public JdbcChatRepository(DataSource ds) {
        super(ds);
    }

    @Override
    public long add(long id) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement("INSERT INTO chat (id) VALUES (?)", new String[]{"id"});
            ps.setLong(1, id);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update("DELETE FROM chat WHERE id=?", id) > 0;
    }

    @Override
    public List<Chat> findAll() {
        return jdbcTemplate.query("SELECT id FROM chat", new BeanPropertyRowMapper<>(Chat.class));
    }
}
