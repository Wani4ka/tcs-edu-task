package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.scrapper.domain.repository.ChatRepositoryTest;

@Nested
@SpringBootTest
public class JpaChatRepositoryTest extends ChatRepositoryTest {
    @Autowired
    protected JpaChatRepositoryTest(JpaChatRepository repository) {
        super(repository);
    }
}
