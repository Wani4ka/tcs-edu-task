package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowQuestionsResponse;

@Service
@RequiredArgsConstructor
public class StackOverflowService {
    private final StackOverflowClient client;

    public Mono<StackOverflowQuestionsResponse> fetchQuestion(int id) {
        return client.fetchQuestion(id);
    }
}
