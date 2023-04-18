package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.configuration.Scheduler;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.service.BotService;
import ru.tinkoff.edu.java.scrapper.service.GitHubService;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdater;
import ru.tinkoff.edu.java.url.LinkParser;
import ru.tinkoff.edu.java.url.link.GitHubLink;

import java.time.OffsetDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcLinkUpdater implements LinkUpdater {

    private final LinkRepository links;
    private final Scheduler scheduler;
    private final BotService botService;

    private final GitHubService ghService;

    @Override
    @Transactional
    public int update() {
        return links.peekOld(scheduler.maxLinkAge())
                .stream().reduce(0, (a, link) -> a + (update(link) ? 1 : 0), Integer::sum);
    }

    private boolean update(LinkEntity link) {
        var parsed = LinkParser.parseLink(link.getUrl().toString());
        switch (parsed) {
            case GitHubLink ghLink -> {
                var lastUpdate = ghService.getLastUpdate(ghLink.user(), ghLink.repo());
                if (lastUpdate != null && lastUpdate.getCreatedAt().isAfter(OffsetDateTime.now(lastUpdate.getCreatedAt().getOffset()))) {
                    sendUpdate(link, lastUpdate.getCreatedAt(), lastUpdate.getType().getDescription());
                    return true;
                }
                return false;
            }
            case null -> throw new IllegalArgumentException("Invalid link");
            default -> throw new IllegalStateException("Unexpected value: " + parsed);
        }
    }

    private void sendUpdate(LinkEntity link, OffsetDateTime lastEvent, String description) {
        links.updateLastEvent(link.getId(), lastEvent);
        log.info("Update for link {}: {} at {}", link.getId(), description, lastEvent);
        botService.sendUpdate(link.getId(), link.getUrl(), lastEvent, description);
    }
}
