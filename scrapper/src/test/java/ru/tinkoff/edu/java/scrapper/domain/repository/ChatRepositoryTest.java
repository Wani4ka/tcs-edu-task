package ru.tinkoff.edu.java.scrapper.domain.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.TestConstants;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ChatRepositoryTest extends IntegrationEnvironment {
    protected static final Set<Long> template = Set.copyOf(TestConstants.IDS);

    protected final ChatRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testAddChat() {
        template.forEach(repository::add);
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveChat() {
        template.forEach(repository::add);
        assertTrue(repository.remove(template.stream().findFirst().orElseThrow()));
    }
}
