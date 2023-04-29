package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import ru.tinkoff.edu.java.scrapper.domain.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.ChatRepository;

import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Chat.CHAT;

@RequiredArgsConstructor
public class JooqChatRepository implements ChatRepository {

    private final DSLContext context;

    @Override
    public void add(long id) {
        context.insertInto(CHAT)
                .set(CHAT.ID, id)
                .onConflictDoNothing()
                .execute();
    }

    @Override
    public int remove(long id) {
        return context.deleteFrom(CHAT)
                .where(CHAT.ID.eq(id))
                .execute();
    }

    @Override
    public ChatEntity findById(long id) {
        return context.select(CHAT.fields())
                .from(CHAT)
                .where(CHAT.ID.eq(id))
                .fetchOneInto(ChatEntity.class);
    }

    @Override
    public List<ChatEntity> findAll() {
        return context.select(CHAT.fields())
                .from(CHAT)
                .fetchInto(ChatEntity.class);
    }
}
