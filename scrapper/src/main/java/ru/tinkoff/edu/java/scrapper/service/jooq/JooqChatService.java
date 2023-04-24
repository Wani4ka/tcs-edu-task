package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@RequiredArgsConstructor
public class JooqChatService implements ChatService {
    private final JooqChatRepository repository;

    @Override
    public void register(long tgChatId) {
        repository.add(tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        repository.remove(tgChatId);
    }
}
