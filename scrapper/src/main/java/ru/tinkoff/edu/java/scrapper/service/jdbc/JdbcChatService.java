package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.domain.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@Service("jdbc_userService")
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {

    private final JdbcChatRepository repository;

    @Override
    public void register(long tgChatId) {
        repository.add(tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        repository.remove(tgChatId);
    }
}
