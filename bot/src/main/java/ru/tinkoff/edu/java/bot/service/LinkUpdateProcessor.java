package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.chat.Telegram;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;

@Service
@RequiredArgsConstructor
public class LinkUpdateProcessor {

    private final Telegram telegram;

    public void process(LinkUpdateRequest request) {
        throw new RuntimeException("oh no, i broke");
//        var msg = String.format("Detected an update for link %s:\n%s", request.url(), request.description());
//        request.tgChatIds().forEach(chatId -> telegram.sendMessage(chatId, msg));
    }

}
