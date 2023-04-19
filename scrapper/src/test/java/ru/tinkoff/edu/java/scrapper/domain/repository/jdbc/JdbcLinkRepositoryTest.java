package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.TestConstants;

import java.net.URI;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JdbcLinkRepositoryTest extends IntegrationEnvironment {
    @Autowired
    private JdbcLinkRepository linkRepo;

    private final Set<URI> template = Set.copyOf(TestConstants.URIS);

    @Test
    @Transactional
    @Rollback
    public void testAddLink() {
        template.forEach(linkRepo::add);
        assertThrows(DuplicateKeyException.class, () -> linkRepo.add(template.iterator().next()));
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveLink() {
        template.forEach(linkRepo::add);
        var link = linkRepo.findByUrl(template.iterator().next());
        assertTrue(linkRepo.remove(link.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllLinks() {
        template.forEach(linkRepo::add);
        var links = linkRepo.findAll();
        assertEquals(links.size(), template.size());
        assertAll(links.stream().map(link -> () -> assertTrue(template.contains(link.getUrl()))));
    }
}
