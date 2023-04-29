package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepositoryTest;

@Nested
@SpringBootTest
public class JooqLinkRepositoryTest extends LinkRepositoryTest {
    @Autowired
    public JooqLinkRepositoryTest(JooqLinkRepository repository) {
        super(repository);
    }
}
