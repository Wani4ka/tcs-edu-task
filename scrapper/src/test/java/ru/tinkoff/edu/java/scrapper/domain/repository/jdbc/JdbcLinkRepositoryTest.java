package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.TestConstants;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

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
        var ids = template.stream().map(linkRepo::add).collect(Collectors.toSet());
        assertEquals(template.size(), ids.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveLink() {
        var ids = template.stream().map(linkRepo::add).collect(Collectors.toSet());
        assertTrue(linkRepo.remove(ids.stream().findFirst().orElseThrow()));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllLinks() {
        var ids = template.stream()
            .map(uri -> new Link(linkRepo.add(uri), uri)).collect(Collectors.toSet());
        var found = linkRepo.findAll();
        assertEquals(ids.size(), found.size());
        assertAll(found.stream().map(link -> () -> assertTrue(ids.contains(link))));
    }
}
