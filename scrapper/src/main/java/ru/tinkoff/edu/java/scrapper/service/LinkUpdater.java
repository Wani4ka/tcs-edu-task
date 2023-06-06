package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.configuration.Scheduler;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.service.external.GitHubService;
import ru.tinkoff.edu.java.scrapper.service.external.LinkUpdateChecker;
import ru.tinkoff.edu.java.scrapper.service.external.StackOverflowService;
import ru.tinkoff.edu.java.url.LinkParser;
import ru.tinkoff.edu.java.url.link.GitHubLink;
import ru.tinkoff.edu.java.url.link.StackOverflowLink;

import java.time.OffsetDateTime;

@Slf4j
@RequiredArgsConstructor
public class LinkUpdater {
    private final LinkRepository links;
    private final Scheduler scheduler;
    private final BotService botService;

    private final GitHubService ghService;
    private final StackOverflowService soService;

    @Transactional
    public int update() {
        return links.peekOld(scheduler.maxLinkAge())
                .stream().reduce(0, (a, link) -> a + (update(link) ? 1 : 0), Integer::sum);
    }

    private boolean update(LinkEntity link) {
        var parsed = LinkParser.parseLink(link.getUrl().toString());
        LinkUpdateChecker.LinkUpdate update;
        if (parsed instanceof GitHubLink) {
            update = ghService.checkForUpdates((GitHubLink) parsed, link.getLastEvent());
        } else if (parsed instanceof StackOverflowLink) {
            update = soService.checkForUpdates((StackOverflowLink) parsed, link.getLastEvent());
        } else {
            throw new IllegalArgumentException("Invalid link");
        }
        if (update != null) {
            sendUpdate(link, update.time(), update.description());
            return true;
        }
        return false;
    }

    private void sendUpdate(LinkEntity link, OffsetDateTime lastEvent, String description) {
        links.updateLastEvent(link.getId(), lastEvent);
        log.info("Update for link {}: {} at {}", link.getId(), description, lastEvent);
        botService.sendUpdate(link.getId(), link.getUrl(), lastEvent, description);
    }
}
