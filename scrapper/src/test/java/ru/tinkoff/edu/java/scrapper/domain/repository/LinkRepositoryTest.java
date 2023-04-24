package ru.tinkoff.edu.java.scrapper.domain.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.TestConstants;

import java.net.URI;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class LinkRepositoryTest extends IntegrationEnvironment {
    protected static final Set<URI> template = Set.copyOf(TestConstants.URIS);


    protected final LinkRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testAddLink() {
        template.forEach(repository::add);
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveLink() {
        template.forEach(repository::add);
        var link = repository.findByUrl(template.iterator().next());
        assertTrue(repository.remove(link.getId()) > 0);
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllLinks() {
        template.forEach(repository::add);
        var links = repository.findAll();
        assertEquals(links.size(), template.size());
        assertAll(links.stream().map(link -> () -> assertTrue(template.contains(link.getUrl()))));
    }
}
