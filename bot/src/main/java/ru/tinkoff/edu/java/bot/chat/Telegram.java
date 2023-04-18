package ru.tinkoff.edu.java.bot.chat;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
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
        var commands = processor.getCommands().stream()
                        .map(cmd -> new BotCommand(cmd.getName(), cmd.getDescription()))
                        .toArray(BotCommand[]::new);
        bot.execute(new SetMyCommands(commands));
    }

    public void sendMessage(long chatId, String text) {
        bot.execute(new SendMessage(chatId, text));
    }
}
