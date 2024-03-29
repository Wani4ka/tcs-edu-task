package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.scrapper.domain.repository.ChatRepositoryTest;

@Nested
@SpringBootTest
public class JdbcChatRepositoryTest extends ChatRepositoryTest {
    @Autowired
    protected JdbcChatRepositoryTest(JdbcChatRepository repository) {
        super(repository);
    }
}
