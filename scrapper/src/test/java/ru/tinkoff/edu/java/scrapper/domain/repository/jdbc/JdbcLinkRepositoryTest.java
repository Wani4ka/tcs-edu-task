package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepositoryTest;

@Nested
@SpringBootTest
public class JdbcLinkRepositoryTest extends LinkRepositoryTest {
    @Autowired
    public JdbcLinkRepositoryTest(JdbcLinkRepository repository) {
        super(repository);
    }
}
