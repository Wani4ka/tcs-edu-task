package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepositoryTest;

@Nested
@SpringBootTest
public class JpaLinkRepositoryTest extends LinkRepositoryTest {
    @Autowired
    public JpaLinkRepositoryTest(JpaLinkRepository repository) {
        super(repository);
    }
}
