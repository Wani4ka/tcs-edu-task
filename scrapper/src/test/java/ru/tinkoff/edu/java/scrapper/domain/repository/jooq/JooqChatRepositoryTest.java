package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.scrapper.domain.repository.ChatRepositoryTest;

@Nested
@SpringBootTest
public class JooqChatRepositoryTest extends ChatRepositoryTest {
    @Autowired
    protected JooqChatRepositoryTest(JooqChatRepository repository) {
        super(repository);
    }
}
