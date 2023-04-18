package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class JdbcRepository {
    protected final JdbcTemplate jdbcTemplate;

    protected JdbcRepository(DataSource ds) {
        this(new JdbcTemplate(ds));
    }
}
