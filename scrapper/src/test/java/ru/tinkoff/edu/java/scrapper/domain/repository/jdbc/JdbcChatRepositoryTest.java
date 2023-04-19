package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.TestConstants;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JdbcChatRepositoryTest extends IntegrationEnvironment {
    @Autowired
    private JdbcChatRepository chatRepo;

    private final Set<Long> template = Set.copyOf(TestConstants.IDS);

    @Test
    @Transactional
    @Rollback
    public void testAddChat() {
        template.forEach(chatRepo::add);
        assertThrows(DuplicateKeyException.class, () -> chatRepo.add(template.iterator().next()));
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveChat() {
        template.forEach(chatRepo::add);
        assertTrue(chatRepo.remove(template.stream().findFirst().orElseThrow()));
    }
}
