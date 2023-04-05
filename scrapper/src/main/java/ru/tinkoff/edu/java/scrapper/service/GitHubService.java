package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.dto.GitHubRepoResponse;

@Service
@RequiredArgsConstructor
public class GitHubService {
    private final GitHubClient client;

    public Mono<GitHubRepoResponse> fetchRepository(String author, String repo) {
        return client.fetchRepository(author, repo);
    }
}
