package ru.tinkoff.edu.java.scrapper.service.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackOverflowQuestionResponse;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackOverflowQuestionsResponse;
import ru.tinkoff.edu.java.url.link.StackOverflowLink;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class StackOverflowService implements LinkUpdateChecker<StackOverflowLink> {
    private final StackOverflowClient client;

    public Mono<StackOverflowQuestionsResponse> fetchQuestions(String... ids) {
        return client.fetchQuestion(String.join(";", ids));
    }

    public Mono<StackOverflowQuestionResponse> fetchQuestion(String id) {
        return fetchQuestions(id).map(resp -> resp.items().get(0));
    }

    @Override
    public LinkUpdate checkForUpdates(StackOverflowLink link, OffsetDateTime lastEvent) {
        var question = fetchQuestion(link.id()).block();
        if (question != null && question.lastActivityDate().isAfter(lastEvent)) {
            return new LinkUpdate(question.lastActivityDate(), "A StackOverflow question updated");
        }
        return null;
    }
}
