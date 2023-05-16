package ru.tinkoff.edu.java.scrapper.service.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubEventResponse;
import ru.tinkoff.edu.java.url.link.GitHubLink;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubService implements LinkUpdateChecker<GitHubLink> {
    private final GitHubClient client;

    public Mono<List<GitHubEventResponse>> fetchRepository(String author, String repo) {
        return client.fetchEvents(author, repo);
    }

    public GitHubEventResponse getLastUpdate(String author, String repo) {
        var events = fetchRepository(author, repo).block();
        if (events == null || events.isEmpty()) {
            return null;
        }
        return events.get(0);
    }

    @Override
    public LinkUpdate checkForUpdates(GitHubLink link, OffsetDateTime lastEvent) {
        var lastUpdate = getLastUpdate(link.user(), link.repo());
        if (lastUpdate != null && lastUpdate.getCreatedAt().isAfter(lastEvent)) {
            return new LinkUpdate(lastUpdate.getCreatedAt(), lastUpdate.getType().getDescription());
        }
        return null;
    }
}
