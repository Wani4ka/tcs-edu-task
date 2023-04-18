package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackOverflowQuestionResponse;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackOverflowQuestionsResponse;

@Service
@RequiredArgsConstructor
public class StackOverflowService {
    private final StackOverflowClient client;

    public Mono<StackOverflowQuestionsResponse> fetchQuestions(String... ids) {
        return client.fetchQuestion(String.join(";", ids));
    }

    public Mono<StackOverflowQuestionResponse> fetchQuestion(String id) {
        return fetchQuestions(id).map(resp -> resp.items().get(0));
    }
}
