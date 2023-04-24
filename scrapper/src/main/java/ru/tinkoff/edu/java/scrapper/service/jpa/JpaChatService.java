package ru.tinkoff.edu.java.scrapper.service.jpa;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@RequiredArgsConstructor
public class JpaChatService implements ChatService {
    private final JpaChatRepository repository;

    @Override
    public void register(long tgChatId) {
        repository.add(tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        repository.remove(tgChatId);
    }
}
