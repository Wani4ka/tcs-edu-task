package ru.tinkoff.edu.java.scrapper.domain.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.TestConstants;
import ru.tinkoff.edu.java.scrapper.domain.entity.SubscriptionEntity;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SubscriptionRepositoryTest extends IntegrationEnvironment {

    protected static final List<Pair<Long, URI>> template = TestConstants.IDS.stream()
            .mapMulti((BiConsumer<Long, Consumer<Pair<Long, URI>>>) (el, consumer) -> {
                consumer.accept(Pair.of(el, TestConstants.URIS.get((int) (el % TestConstants.URIS.size()))));
                consumer.accept(Pair.of(el, TestConstants.URIS.get((int) ((el * 5) % TestConstants.URIS.size()))));
            }).distinct().toList();

    protected final ChatRepository chatRepository;
    protected final LinkRepository linkRepository;
    protected final SubscriptionRepository subscriptionRepository;

    protected final PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

    private Set<Long> addedChats;
    private BidiMap<URI, Long> addedUris;

    @BeforeEach
    void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
        addedChats = new HashSet<>();
        addedUris = new DualHashBidiMap<>();
    }

    private Long addSubscription(long chat, URI uri) {
        if (!addedChats.contains(chat)) {
            chatRepository.add(chat);
            addedChats.add(chat);
        }
        long linkId = addedUris.computeIfAbsent(uri, (url) -> {
            linkRepository.add(url);
            return linkRepository.findByUrl(url).getId();
        });
        subscriptionRepository.add(chat, linkId);
        return subscriptionRepository.findByData(chat, linkId).getId();
    }

    private Set<Long> addSubscriptions() {
        return template.stream().map(p -> transactionTemplate.execute(status ->
                        addSubscription(p.getLeft(), p.getRight())))
                .filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @Test
    @Rollback
    @Transactional
    void addTest() {
        assertEquals(template.size(), addSubscriptions().size());
    }

    @Test
    @Rollback
    @Transactional
    void removeTest() {
        var id = addSubscription(template.get(0).getLeft(), template.get(0).getRight());
        assertTrue(subscriptionRepository.remove(id) > 0);
    }

    private void assertAllFound(List<SubscriptionEntity> found, Set<Pair<Long, URI>> need) {
        assertEquals(need.size(), found.size());
        assertAll(found.stream()
                .map(sub -> () -> {
                    System.err.println(sub.getChatId() + " " + sub.getLinkId() + " " + addedUris.getKey(sub.getLinkId()));
                    assertTrue(need.contains(Pair.of(sub.getChatId(), addedUris.getKey(sub.getLinkId()))));
                }));
    }

    @Test
    @Rollback
    @Transactional
    void findAllTest() {
        addSubscriptions();
        assertAllFound(subscriptionRepository.findAll(), Set.copyOf(template));
    }

    @Test
    @Rollback
    @Transactional
    void findByChatTest() {
        addSubscriptions();
        var query = template.get(0).getLeft();
        assumeFalse(query == null);
        assertAllFound(
                subscriptionRepository.findByChat(query),
                template.stream().filter(p -> query.equals(p.getLeft())).collect(Collectors.toSet())
        );
    }

    @Test
    @Rollback
    @Transactional
    void findByLinkTest() {
        addSubscriptions();
        var query = template.get(0).getRight();
        assumeFalse(query == null);
        assertAllFound(
                subscriptionRepository.findByLink(addedUris.get(query)),
                template.stream().filter(p -> query.equals(p.getRight())).collect(Collectors.toSet())
        );
    }
}
