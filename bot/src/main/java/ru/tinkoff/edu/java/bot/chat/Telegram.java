package ru.tinkoff.edu.java.bot.chat;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.processor.TelegramProcessor;

@RequiredArgsConstructor
@Component
public class Telegram {
    private final TelegramBot bot;
    private final TelegramProcessor processor;

    @PostConstruct
    public void init() {
        bot.setUpdatesListener(updates -> {
            updates.stream().map(processor::processUpdate).forEach(bot::execute);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
