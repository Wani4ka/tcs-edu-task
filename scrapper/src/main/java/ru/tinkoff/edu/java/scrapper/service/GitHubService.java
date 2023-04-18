package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubEventResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubService {
    private final GitHubClient client;

    public Mono<List<GitHubEventResponse>> fetchRepository(String author, String repo) {
        return client.fetchEvents(author, repo);
    }

    public GitHubEventResponse getLastUpdate(String author, String repo) {
        var events = fetchRepository(author, repo).block();
        if (events == null || events.isEmpty())
            return null;
        return events.get(0);
    }
}
